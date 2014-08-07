package com.schmapty.guvnah.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.schmapty.guvnah.model.rule.impl.RuleSetName;
import com.schmapty.guvnah.service.HtmlCheckerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/context-app.xml")
public class SauceLabsTest {

	@Autowired
	private HtmlCheckerService service;
	@Ignore
	@Test
	public void test() {
		
		DesiredCapabilities dc = DesiredCapabilities.safari();
		dc.setBrowserName("iPhone");
		dc.setPlatform(Platform.MAC);
		
		List<RuleSetName> ruleSetNames = new ArrayList<RuleSetName>();
		ruleSetNames.add(RuleSetName.SECTION508);
		ruleSetNames.add(RuleSetName.WCAG2AAA);
		
		List<String> urls = new ArrayList<String>();
		urls.add("http://www.amazon.com");
		urls.add("http://www.cnn.com");
		urls.add("http://www.foxnews.com");
		urls.add("http://www.samsung.com/us");
		urls.add("http://www.nytimes.com");
		urls.add("http://www.nbcnews.com");

//		Map<String, List<Result>> resultsMap = service.checkHtmlViaSauce(urls, ruleSetNames, Confidence.POTENTIAL, dc);
//		for(String key: resultsMap.keySet()) {
//			for(Result result: resultsMap.get(key)) {
//				System.out.println(key + " : " + result.getRule().getRuleID() + " : " + result.getRule().getRuleName() + " : " + result.getElement().getValue());
//			}	
//		}
	}
	
}
