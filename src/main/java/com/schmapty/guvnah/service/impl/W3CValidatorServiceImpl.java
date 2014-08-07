
package com.schmapty.guvnah.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.schmapty.guvnah.model.expression.ExpressionParameter;
import com.schmapty.guvnah.model.result.ElementDTO;
import com.schmapty.guvnah.model.result.Result;
import com.schmapty.guvnah.model.rule.Confidence;
import com.schmapty.guvnah.model.rule.Rule;
import com.schmapty.guvnah.model.rule.RuleSetGroup;
import com.schmapty.guvnah.model.rule.TagName;
import com.schmapty.guvnah.model.rule.impl.RuleImpl;
import com.schmapty.guvnah.model.rule.impl.RuleSetGroupImpl;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;
import com.schmapty.guvnah.service.W3CValidatorService;

@Service
public class W3CValidatorServiceImpl implements W3CValidatorService {

	Logger logger = LoggerFactory.getLogger(W3CValidatorServiceImpl.class);
	
	String PERL_COMMAND = "/usr/bin/perl";
	String VALIDATOR_SCRIPT = this.getClass().getResource("/META-INF/w3cvalidator.pl").getPath();
	File TEMP_DIR = new File("META-INF/files");
	
	@Override
	public List<Result> validate(Document document, Confidence maxConfidence) {
		
		DocumentType docType = null;
		for(Node n: document.childNodes()) {
			if(n instanceof DocumentType) {
				docType = (DocumentType)n;
				break;
			}
		}
		if(docType!=null) {
			 String publicid = docType.attr("publicid");
			 if(publicid.equals("")) {
				 docType.replaceWith(new DocumentType("html",  "-//W3C//DTD XHTML 1.0 Transitional//EN", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", docType.baseUri()));
			 }
		}
		List<Result> retval = new ArrayList<Result>();
		
		File file = new File(TEMP_DIR.getPath() + "/" + document.hashCode() + "_" + System.currentTimeMillis() + ".html");

		ProcessBuilder builder;
		Process process;
		
		try {
			String documentStr = document.toString();
			documentStr = documentStr.replaceAll("\\r$", "");
			FileUtils.writeStringToFile(file, documentStr, Charset.forName("UTF-8"));
			logger.info(file.getAbsolutePath());
			
			List<String> pbParameters = new ArrayList<String>();
			pbParameters.add(PERL_COMMAND);
			pbParameters.add(VALIDATOR_SCRIPT);
			pbParameters.add(file.getAbsolutePath());
			builder = new ProcessBuilder(pbParameters);
			process = builder.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("UTF-8")));
	        List<String[]> w3cresults = new ArrayList<String[]>();
			String line = null;
	        boolean header = true;
	        while ((line = in.readLine()) != null) {
	        	if(!header) {
	        		String[] r = line.split("\\|");
	        		w3cresults.add(r);
	        		//logger.info(r[0] + " " + r[1] + " " + r[2] + " " + r[3]);
	        	} else {
	        		logger.info("W3C RESULTS: " + line);
	        	}
	        	header = false;
	        }
	        in.close();
	        //FileUtils.deleteQuietly(file);
	        
	        RuleSetGroup group = new RuleSetGroupImpl();
	        group.setId("W3C");
	        group.setTitle("W3C HTML Validation");
	        
	        for(String[] w3cresult: w3cresults) {
	        	if(w3cresult.length==4) {
		        	Rule rule = createRule(w3cresult);
		        	if(rule.getConfidence().value()>maxConfidence.value()) {
		        		continue;
		        	}
		        	RuleSetName ruleSetName = RuleSetName.W3C;
		        	ElementDTO element = new ElementDTO();
		        	element.setLineNumber(new Integer(w3cresult[1]));
		        	element.setColumnNumber(new Integer(w3cresult[2]));
		        	ExpressionParameter<ElementDTO> elementParameter= new ExpressionParameter<ElementDTO>(element);
		        	Result result = new Result(rule, ruleSetName, group, null, elementParameter);
		        	retval.add(result);
	        	}
	        }
			
		} catch(IOException e) {
			logger.error("ERROR validating file", e);
		}
		
		return retval;
	}

	public static String removeUTF8BOM(String s) {
	    if (s.startsWith("\uFEFF")) {
	        s = s.substring(1);
	    }
	    return s;
	}
	
	public Rule createRule(String[] result) {
		Rule rule = new RuleImpl();
		if("E".equals(result[0])) {
			rule.setConfidence(Confidence.ERROR);
		} else if ("W".equals(result[0])) {
			rule.setConfidence(Confidence.LIKELY);
		}
		rule.setTagName(TagName.ALL);
		rule.setDescription(result[3]);
		rule.setName(result[3]);
		rule.setRuleId(result[3].hashCode());
		return rule;
	}
	
}
