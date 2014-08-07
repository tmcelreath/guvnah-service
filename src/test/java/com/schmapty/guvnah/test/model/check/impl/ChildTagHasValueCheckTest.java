package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.ChildTagHasValueCheck;
import com.schmapty.guvnah.model.rule.TagName;

public class ChildTagHasValueCheckTest {

	@Test
	public void testPostitive() {
		String html = "<html><head id = 'test'><title>TITLE</title></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		ChildTagHasValueCheck check = new ChildTagHasValueCheck(TagName.TITLE);
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testNegative() {
		String html = "<html><head id = 'test'><title></title></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		ChildTagHasValueCheck check = new ChildTagHasValueCheck(TagName.TITLE);
		assertFalse(check.execute(element, doc));
	}
	
}
