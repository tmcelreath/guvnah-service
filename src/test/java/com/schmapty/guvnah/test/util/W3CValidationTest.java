package com.schmapty.guvnah.test.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.schmapty.guvnah.model.rule.Confidence;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;
import com.schmapty.guvnah.service.HtmlCheckerService;
import com.schmapty.guvnah.service.SortOrder;
import com.schmapty.guvnah.service.UserAgent;
import com.schmapty.guvnah.service.W3CValidatorService;
import com.schmapty.guvnah.web.controller.CheckRequest;
import com.schmapty.guvnah.web.controller.CheckResponse;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/context-app.xml")
public class W3CValidationTest {

	Logger logger = LoggerFactory.getLogger(W3CValidationTest.class);
	
	@Autowired
	W3CValidatorService service;
	
	@Autowired
	HtmlCheckerService guvnah;
	
	@Test
	public void testW3CValidation() {

		try {
			
			String url = "http://www.cnn.com";
			
			CheckRequest request = new CheckRequest();
			request.setExecutejs(false);
			request.setLevel(Confidence.ERROR);
			request.setOutputAsExcel(false);
			request.setPlatform(UserAgent.DESKTOP);
			request.setRuleset(new RuleSetName[] { RuleSetName.W3C });
			request.setSort(SortOrder.RULE);
			request.setUrl(url);
			
			CheckResponse response = guvnah.checkHtml(request);
			
			System.out.println(response.getResults().size());
			/*
			Connection conn = Jsoup.connect(url);
			Document document = conn.get();
			
			List<Result> results = service.validate(document);
			
			
			String[] lines = document.toString().split("\n");
			for(Result result: results) {
				if(result.getElement().getLineNumber() != null) {
					String line = lines[result.getElement().getLineNumber()-1];
					System.out.println("LINE: " + result.getRule().getRuleDescription() + "|||||" +  + line.substring(0, (result.getElement().getColumnNumber()-1)) + " **** " + line.substring((result.getElement().getColumnNumber()-1)));
					Document doc = Jsoup.parseBodyFragment(line);
					Elements bodyElements =  doc.getElementsByTag(TagName.BODY.value());
					if(bodyElements.size()>0) {
						Element body = bodyElements.get(0);
						Elements children = body.children();
						if(children.size()>0) {
							System.out.println("ELEMENT: " + children.get(0).toString());
							Element e = children.get(0);
							result.getElement().setValue(line);
							result.getElement().setTagName(e.tagName());
						} else {
							System.out.println("****** NO ELEMENT FOUND *********");
						}
					}
					
				}
			}
			*/
			
//			for(Result result : results) {
//				System.out.println(result.getRule().getLevel()+ " " + result.getElement().getLineNumber() + " " + result.getElement().getColumnNumber() + " " + result.getRule().getRuleDescription());
//			}
			/*
			File file = new File(dir.getPath() + "/" + System.currentTimeMillis() + ".html");
			
			FileUtils.writeStringToFile(file, doc.toString());
			
			logger.info(file.getAbsolutePath());
			
			String command = "perl " + script.getPath() + " " + file.getAbsolutePath();
			process = Runtime.getRuntime().exec(removeUTF8BOM(command));
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String line = null;
	        List<String[]> results = new ArrayList<String[]>();
	        boolean header = true;
	        while ((line = in.readLine()) != null) {
	        	String[] r = line.split("\\|");
	        	if(!header) {
	        		results.add(r);
	        		logger.info(r[0] + " " + r[1] + " " + r[2] + " " + r[3]);
	        	}
	        	header = false;
	        }
	        in.close();
	        FileUtils.deleteQuietly(file);
	        */

		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	
	public static String removeUTF8BOM(String s) {
	    if (s.startsWith("\uFEFF")) {
	        s = s.substring(1);
	    }
	    return s;
	}
	
}
