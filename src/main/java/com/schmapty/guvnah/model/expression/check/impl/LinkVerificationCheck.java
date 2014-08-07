package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LinkVerificationCheck extends Check {
	
	Logger logger = LoggerFactory.getLogger(LinkVerificationCheck.class);
	
	@Override
	public boolean execute(Element element, Document root) {
		String href = element.attr("href").toLowerCase();
		if(!element.tagName().equals(TagName.ANCHOR.value())
			|| !element.hasAttr("href")) {
				return true;
		}
		if( href.startsWith("#")
			|| href.startsWith("javascript:")
			|| href.startsWith("mailto:")) {
			return true;
		}
		href = element.absUrl("href");
		if(href.startsWith("#")||href.startsWith("javascript:")) return true;
		try {
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(href);
		 
			// add request header
			//request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if(code==HttpServletResponse.SC_OK) {
				logger.info("HREF: " + href);
			} else {
				logger.error("HREF: " + href + " | CODE=" + code);
			}
			logger.info("HREF: " + href);
			switch(code) {
				case HttpServletResponse.SC_OK:
					return false;
				case HttpServletResponse.SC_NOT_FOUND:
					return false;
				default:
					if(code==HttpServletResponse.SC_MOVED_PERMANENTLY
					 	|| code==HttpServletResponse.SC_FOUND
					 	|| code==HttpServletResponse.SC_NOT_MODIFIED) {
						
						BufferedReader rd = new BufferedReader(
								new InputStreamReader(response.getEntity().getContent()));
						 
							StringBuffer result = new StringBuffer();
							String line = "";
							while ((line = rd.readLine()) != null) {
								result.append(line);
							}
							logger.error(result.toString());
							return false;
					} else {
						return false;
					}
			}
			/*
			Document doc = Jsoup.connect(href).get();
			String title = "";
			if (doc.getElementsByTag(TagName.TITLE.value()).size()>0) {
				title = doc.getElementsByTag(TagName.TITLE.value()).get(0).text();
			}
			logger.info("HREF: " + href + " | TITLE: " + title);
			if(doc.getElementsByTag(TagName.BODY.value()).get(0).text().equals(href)) {
				return false;
			}
			*/
		} catch (Exception e) {
			logger.error("*****BROKEN LINK *****: " + element.attr("href"), e);
			return false;
		}
	}

}
