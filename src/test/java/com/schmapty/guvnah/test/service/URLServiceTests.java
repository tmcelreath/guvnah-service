package com.schmapty.guvnah.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.schmapty.guvnah.model.result.SortedResultDTO;
import com.schmapty.guvnah.model.rule.Confidence;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;
import com.schmapty.guvnah.service.HtmlCheckerService;
import com.schmapty.guvnah.service.SortOrder;
import com.schmapty.guvnah.service.UserAgent;
import com.schmapty.guvnah.web.controller.CheckRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/context-app.xml")
public class URLServiceTests {

	Logger logger = LoggerFactory.getLogger(URLServiceTests.class);
	
	@Autowired
	private HtmlCheckerService service;
	
	@Test
	public void test() {
		try {
			String url = "http://www.foxnews.com/";		
			RuleSetName[] ruleSetNames = new RuleSetName[] { RuleSetName.WCAG2AAA, RuleSetName.SECTION508 };
			logger.info("******************** CHECK HTML ************************");
			CheckRequest request = new CheckRequest();
			request.setUrl(url);
			request.setExecutejs(Boolean.TRUE);
			request.setLevel(Confidence.POTENTIAL);
			request.setOutputAsExcel(Boolean.FALSE);
			request.setPlatform(UserAgent.DESKTOP);
			request.setSort(SortOrder.RULE);
			request.setRuleset(ruleSetNames);
			@SuppressWarnings("unused")
			List<SortedResultDTO> results = service.checkHtml(request).getResults();
			logger.info("********************************************************");
			logger.info("******************** PRINT RESULTS *********************");
			//for(SortedResultDTO result: results) {
				//logger.error(result.getRule().getLevel() + " | LINE: " + result.getElement().getLineNumber() + " | CHAR: " + result.getElement().getColumnNumber() + " | RULESET: " + result.getRule().getRuleSetName() + " | RULESET GROUP: " + result.getRule().getRuleSetGroupID() + " | RULESET SUB GROUP: " + result.getRule().getRuleSetSubGroupID() +  " | RULE: " + result.getRule().getRuleID() + " : " + result.getRule().getRuleName() + " : " + result.getElement().getValue());
			//}
			logger.info("********************************************************");
		} catch (Exception e) {
			logger.error("ERROR!", e);
		}
	}
	
}
