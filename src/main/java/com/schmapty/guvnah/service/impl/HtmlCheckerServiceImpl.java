package com.schmapty.guvnah.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.schmapty.guvnah.model.expression.ExpressionParameter;
import com.schmapty.guvnah.model.result.ElementDTO;
import com.schmapty.guvnah.model.result.ElementResultDTO;
import com.schmapty.guvnah.model.result.Result;
import com.schmapty.guvnah.model.result.ResultDTO;
import com.schmapty.guvnah.model.result.RuleDTO;
import com.schmapty.guvnah.model.result.RuleResultDTO;
import com.schmapty.guvnah.model.result.SortedResultDTO;
import com.schmapty.guvnah.model.rule.Confidence;
import com.schmapty.guvnah.model.rule.RuleExecutor;
import com.schmapty.guvnah.model.rule.RuleSetGroup;
import com.schmapty.guvnah.model.rule.TagName;
import com.schmapty.guvnah.model.rule.impl.RuleImpl;
import com.schmapty.guvnah.model.rule.impl.RuleSetGroupImpl;
import com.schmapty.guvnah.model.rule.impl.RuleSetImpl;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;
import com.schmapty.guvnah.service.HtmlCheckerService;
import com.schmapty.guvnah.service.SortOrder;
import com.schmapty.guvnah.service.UserAgent;
import com.schmapty.guvnah.service.W3CValidatorService;
import com.schmapty.guvnah.web.controller.CheckRequest;
import com.schmapty.guvnah.web.controller.CheckResponse;

@Service
public class HtmlCheckerServiceImpl implements HtmlCheckerService {

	Logger logger = LoggerFactory.getLogger(HtmlCheckerServiceImpl.class);
	
	@Resource(name="ruleSetMap")
	private Map<RuleSetName, RuleSetImpl> ruleSetMap;

	@Autowired @Qualifier("phantomJSPath") 
	private String PHANTOMJS_EXECUTABLE_PATH;
	
	@Autowired
	W3CValidatorService w3cService;
	
	private static final String HEADER_KEY_AUTHORIZATION = "Authorization";
	private static final String HEADER_KEY_PHANTOMJS_USERAGENT = "phantomjs.page.settings.userAgent";

	@Override
	public CheckResponse checkHtml(CheckRequest request) {

		CheckResponse retval = new CheckResponse();
		List<SortedResultDTO> sortedResults = new ArrayList<SortedResultDTO>();
		
		Document document = getDocument(request.getUrl(), request.getUsername(), request.getPassword(), request.getPlatform(), request.getExecutejs());
		List<Result> results  = check(document, Arrays.asList(request.getRuleset()), request.getLevel());
		
		List<RuleDTO> rules = new ArrayList<RuleDTO>();
		List<ElementDTO> elements = new ArrayList<ElementDTO>();
		
		for(Result result: results) {
			if(!rules.contains(result.getRule())) {
				rules.add(result.getRule());
			}
			if(!rules.contains(result.getElement())) {
				elements.add(result.getElement());
			}
			if(request.getSort().equals(SortOrder.ELEMENT)) {
				addElementResult(sortedResults, result.getElement(), result.getRule());
			} else if(request.getSort().equals(SortOrder.RULE)){
				addRuleResult(sortedResults, result.getRule(), result.getElement());
			}
		}
		for(SortedResultDTO dto: sortedResults) {
			if(dto instanceof ElementResultDTO) {
				Collections.sort(((ElementResultDTO)dto).getValue());
			} else if(dto instanceof RuleResultDTO) {
				Collections.sort(((RuleResultDTO)dto).getValue());
			}
		}
		Collections.sort(sortedResults);
		
		// Split HTML source into an array
		ArrayList<String> html = new ArrayList<String>(Arrays.asList(document.toString().split("\n")));
		
		retval.setResults(sortedResults);
		retval.setNumberOfResults(results.size());
		retval.setNumberOfElements(elements.size());
		retval.setNumberOfRules(rules.size());
		retval.setHtml(html);
		retval.setMessage("Success");
		
		return retval;
	}	
	
	
	private Document getDocument(String url, String username, String password, UserAgent userAgent, Boolean executeJS) {
		Document retval = null;
		logger.info("Begin fetching URL: " + url);

		String auth = getBasicAuth(username, password);	
		if(executeJS) {
			retval = getDocumentPhantomJS(url, userAgent, auth);
		} else {
			retval = getDocumentJsoup(url, userAgent, auth);
		}
		logger.info("End fetching URL: " + url);
		/*
	    logger.error("************************************* START SOURCE *********************************************");
	    logger.error("************************************************************************************************");
	    logger.error("************************************************************************************************");
	    logger.error(document.toString());
	    logger.error("************************************** END SOURCE **********************************************");
	    logger.error("************************************************************************************************");
	    logger.error("************************************************************************************************");
	    */
		return retval;
	}
	
	private String getBasicAuth(String username, String password) {
		if(username!=null && !username.trim().equals("") && password!=null && !password.trim().equals("")) {
			return "Basic " + new String(Base64.encodeBase64((username + ":" + password).getBytes()));
		}
		return null;
	}
 	
	private Document getDocumentJsoup(String url, UserAgent userAgent, String auth) {
		Document retval = null;
		try {
			Connection conn = Jsoup.connect(url).userAgent(userAgent.value());
			if(auth!=null) { 
				conn.header(HEADER_KEY_AUTHORIZATION, auth);
			}
			retval = conn.get();
		} catch(IOException e) {
			logger.error("Error fetching URL through Jsoup.", e);
		}
		return retval;
	}
	
	private Document getDocumentPhantomJS(String url, UserAgent userAgent, String auth) {
		Document retval = null;
		PhantomJSDriver driver = null;
		try {
			DesiredCapabilities caps = DesiredCapabilities.phantomjs();
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOMJS_EXECUTABLE_PATH);
			caps.setCapability(HEADER_KEY_PHANTOMJS_USERAGENT, userAgent.value());				
			caps.setJavascriptEnabled(true);
			caps.setCapability("loadImages", false);
			if(auth!=null) {
				caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + HEADER_KEY_AUTHORIZATION, auth);
			}
			driver = new PhantomJSDriver(caps);
			driver.get(url);
			((JavascriptExecutor)driver).executeScript("scrollTo(0,20000)");
			String html = driver.getPageSource();
			retval = Jsoup.parse(html, url);
		} catch (Exception e) {
			logger.error("Error fetching URL through PhantonJS.", e);
		} finally {	    
			if(driver!=null) {
				SessionId id = driver.getSessionId();
				logger.info("BEGIN closing phantomjs driver session:" + id);
				driver.close();
				driver.quit();
	    		driver = null;
	    		logger.info("END closing phantomjs driver session:" + id);
			}
	    }
		return retval;
	}
	
	private List<Result> check(Document document, List<RuleSetName> ruleSetNames, Confidence maxConfidence) {
		List<Result> retval = new ArrayList<Result>();
		for(RuleSetName ruleSetName: ruleSetNames) {
			
			if(ruleSetName.equals(RuleSetName.W3C)) {
				List<Result> results = w3cService.validate(document, maxConfidence);
				String[] lines = document.toString().split("\n");
				for(Result result: results) {
					if(result.getElement().getLineNumber() != null) {
						String line = lines[result.getElement().getLineNumber()-1];
						result.getElement().setValue(line);
						System.out.println("LINE: " /*+ result.getRule().getRuleDescription() + "|||||" +*/  + line.substring(0, (result.getElement().getColumnNumber()-1)) + " **** " + line.substring((result.getElement().getColumnNumber()-1)));
						Document doc = Jsoup.parseBodyFragment(line);
						Elements bodyElements =  doc.getElementsByTag(TagName.BODY.value());
						if(bodyElements.size()>0) {
							Element body = bodyElements.get(0);
							Elements children = body.children();
							if(children.size()>0) {
								//System.out.println("ELEMENT: " + children.get(0).toString());
								Element e = children.get(0);
								result.getElement().setTagName(e.tagName());
								result.getElement().setUrl(document.baseUri());
								Map<String, String> attrs = new HashMap<String, String>();
								for(Attribute a: e.attributes()) {
									attrs.put(a.getKey(), a.getValue());
								}
								result.getElement().setAttrs(attrs);
							} else {
								System.out.println("****** NO ELEMENT FOUND *********");
							}
						}
					}
				}
				retval.addAll(results);
			} else {
			
				RuleSetImpl ruleSet = ruleSetMap.get(ruleSetName);
				for(RuleSetGroupImpl ruleSetGroup: ruleSet.getRuleSetGroups()) {
					if(ruleSetGroup.getRules() != null) {
						retval.addAll(processRules(ruleSetGroup.getRules(), ruleSetName, ruleSetGroup, null, maxConfidence, document));
					}
					if(ruleSetGroup.getRuleSetGroups() != null) {
						for(RuleSetGroupImpl ruleSetSubGroup: ruleSetGroup.getRuleSetGroups()) {
							if(ruleSetSubGroup.getRules() != null) {
								retval.addAll(processRules(ruleSetSubGroup.getRules(), ruleSetName, ruleSetGroup, ruleSetSubGroup, maxConfidence, document));
							}
						}
					}
				}
			}
		}
		return retval;
	}
	
	private List<Result> processRules(List<RuleImpl> rules, RuleSetName ruleSetName, RuleSetGroup ruleSetGroup, RuleSetGroup ruleSetSubGroup, Confidence maxConfidence, Document document) {
		List<Result> retval = new ArrayList<Result>();
		ExpressionParameter<Document> documentParam = new ExpressionParameter<Document>(document);
		for(RuleImpl rule: rules) {
			if(rule.getConfidence().value() <= maxConfidence.value()) {
				List<Element> elements = null;
				if(rule.getTagName().equals(TagName.ALL)) {
					elements = document.getAllElements();
				}else {
					elements = document.getElementsByTag(rule.getTagName().value());
				}
				for(Element element: elements) {
					ExpressionParameter<Element> elementParam = new ExpressionParameter<Element>(element);
					RuleExecutor ruleExecutor = new RuleExecutor(rule, ruleSetName, ruleSetGroup, ruleSetSubGroup, elementParam, documentParam);
					Result result = null;
					try {
						result = ruleExecutor.call();
						if(result != null) {
							retval.add(result);
						}
					} catch (Exception e) {
						logger.error("Exception calling rule executor:",e);
					}
				}
			}
		}
		addLineNumbers(document, retval);
		return retval;
	}
	
	/**
	 * After generating the results, split the original docuemnt into lines
	 * and determine the position (line, char) of each result.
	 * 
	 * Keep in mind that the same tag can exist in multiple parts of the document
	 * and fail multiple rules.
	 * 
	 * @param document
	 * @param results
	 */
	private void addLineNumbers(Document document, List<Result> results) {
		
		// Split the doc into lines
		String[] lines = document.toString().split("\n");
		
		// Initialize the tracker map, this will record each time a location [linenum, char] is
		// used for a particular element string for a raticulat rule.
		// The value of the list elements is fomatted as 'ruleid|linenum|charnum'
		Map<String, List<String>> tracker = new HashMap<String, List<String>>();
		
		for(Result r: results) {
			String s = r.getElement().getValue();
			// If the character is multi-line, only fetch up to the first line break.
			if(s.indexOf("\n")>-1) {
				s = s.substring(0, s.indexOf("\n"));
			}
			// Initialize the entry in the tracker map.
			if(tracker.get(s)==null) {
				tracker.put(s, new ArrayList<String>());
			}
			for(int i=0;i<lines.length;i++) {
				int j = lines[i].indexOf(s);
				while(j > -1) {
					String location = "" + r.getRule().getRuleID()+"|"+i+"|"+j;
					// If this rule|linenum|charnum combo aredy exists for this element,
					// then find the next instance of the element on the current line (if any)
					if(tracker.get(s).contains(location)) {
						j=s.indexOf(s, j+s.length());
					} 
					// Otherwise, we've found the location, and can record it into the Result object.
					else {
						tracker.get(s).add(location);
						r.getElement().setLineNumber(i+1);
						r.getElement().setColumnNumber(j);
						break;
					}
				}
				// If the result location is populated, break out to the next result.
				if(r.getElement().getLineNumber() != null) {
					break;
				}
			}
		}
	}


	
	private void addElementResult(List<SortedResultDTO> list, ElementDTO key, RuleDTO value) {
		for(ResultDTO result: list) {
			ElementDTO k = ((ElementResultDTO)result).getKey();
			if(k.equals(key)) {
				((ElementResultDTO)result).addValue(value);
				return;
			}
		}
		ElementResultDTO e = new ElementResultDTO(key);
		e.addValue(value);
		list.add(e);
	}
	
	private void addRuleResult(List<SortedResultDTO> list, RuleDTO key, ElementDTO value) {
		for(ResultDTO result: list) {
			RuleDTO k = ((RuleResultDTO)result).getKey();
			if(k.equals(key)) {
				((RuleResultDTO)result).addValue(value);
				return;
			}
		}
		RuleResultDTO e = new RuleResultDTO(key);
		e.addValue(value);
		list.add(e);
	}

}
	
