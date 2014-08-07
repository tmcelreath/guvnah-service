package com.schmapty.guvnah.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

import com.schmapty.guvnah.model.rule.TagName;
import com.schmapty.guvnah.service.UserAgent;

public class SiteCrawler {

	UserAgent agent = UserAgent.DESKTOP;
	String baseurl = "http://www.samsung.com";
	
	@Ignore
	@Test
	public void crawl() {
		List<String> results = new ArrayList<String>();
		results = getLinks(baseurl, results);
		for(String result: results) {
			System.out.println(result);
		}
	}

	public List<String> getLinks(String url, List<String> results) {
		System.out.println(url);
		if(url.endsWith("/")) url=url.substring(0, url.length()-1);
		Connection conn = Jsoup.connect(url).userAgent(agent.value());
		Document doc = null;
		try {
			doc = conn.get();
		} catch (IOException e) {
			System.out.println("ERROR FETCHING URL: " + url);
			return results;
			//TODO: ADD LOGGING
		}
		Elements anchors =  doc.getElementsByTag(TagName.ANCHOR.value());
		for(Element anchor : anchors) {
			boolean add = false;
			String href = anchor.attr("href");
			if(href.startsWith("/")) {
				href = baseurl+href;
				add = true;
			} else if (href.startsWith(baseurl)) {
				add = true;
			}
			if(href.endsWith("/")) {
				href = href.substring(0, href.length()-1);
			}
			if(add && !results.contains(href) && !href.equals(url)) {
				results.add(href);
				getLinks(href, results);
			}
		}
		return results;
	}
	
}
