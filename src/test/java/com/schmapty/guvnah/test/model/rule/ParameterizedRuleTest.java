package com.schmapty.guvnah.test.model.rule;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.schmapty.guvnah.model.expression.Expression;
import com.schmapty.guvnah.model.expression.ExpressionParameter;
import com.schmapty.guvnah.model.rule.impl.RuleImpl;
import com.schmapty.guvnah.test.util.SpreadsheetData;

@RunWith(Parameterized.class)
public class ParameterizedRuleTest {

	Logger logger = LoggerFactory.getLogger(ParameterizedRuleTest.class);
	
	private static ApplicationContext ctx;
	
	public static final String ELEMENT_ID = "test";
	
	static {
		ctx = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/context-app.xml");
	}
	
	private Integer ruleId;
	private Boolean result;
	private String html;
	
	@Parameters
	public static Collection<Object[]> data() {
		try {
			InputStream spreadsheet = new FileInputStream("src/test/resources/rule_tests.xls");
        	Collection<Object[]> retval =  new SpreadsheetData(spreadsheet).getData();
        	return retval;
		} catch (IOException e) {
			LoggerFactory.getLogger(ParameterizedRuleTest.class).error("Could not open or parse excel file.", e);
			return null;
		}
	}
	
	public ParameterizedRuleTest(Number ruleId, Boolean result, String html) {
		this.ruleId=ruleId.intValue();
		this.result=result;
		this.html=html;
	}
	
	
	@Test
	public void testRule() {
		
		RuleImpl rule = (RuleImpl)ctx.getBean("rule"+ruleId.toString());
		Document document = Jsoup.parse(html);
		Element element = document.getElementById(ELEMENT_ID);
		ExpressionParameter<Document> documentParam = new ExpressionParameter<Document>(document);
		ExpressionParameter<Element> elementParam = new ExpressionParameter<Element>(element);
		
		if(rule.getPrerequisites()!=null) {
			for(Expression prerequisite: rule.getPrerequisites()) {
				if(!prerequisite.execute(elementParam, documentParam)) {
					logger.error("PREREQUISITE FAILED { ruleId: " + rule.getRuleId() + ", ruleName: " + rule.getName() +  ", prerequisite: " + prerequisite.getClass().getName() + ", html: \"" + html + "\", expectedResult: " + result +  "}");
					// If a prerequisite fails, the rule will always return true - assert that this is the case, and do not test the rule checks.
					assertTrue(result);
					return;
				}
			}
		}
		try {
			boolean ruleResult = rule.execute(elementParam, documentParam);
			assertTrue(ruleResult==result);
		} catch (AssertionError e) {
			logger.error("TEST FAILED { ruleId: " + rule.getRuleId() + ", ruleName: " + rule.getName() +  ", html: \"" + html + "\", expectedResult: " + result +  "}");
			throw e;
		}
		logger.error("TEST PASSED { ruleId: " + rule.getRuleId() + ", ruleName: " + rule.getName() +  ", html: \"" + html + "\", expectedResult: " + result +  "}");
		
	}
	
}
