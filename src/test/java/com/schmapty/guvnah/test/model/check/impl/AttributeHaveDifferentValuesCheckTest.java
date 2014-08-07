package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.AttributesHaveDifferentValuesCheck;

public class AttributeHaveDifferentValuesCheckTest {

	@Test
	public void testPostitive() {
		String html = "<html><head></head><body><img id='test' alt='ONE' src='TWO'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributesHaveDifferentValuesCheck check = new AttributesHaveDifferentValuesCheck("alt", "src");
		assertTrue(check.execute(element, doc));
	}
	
	@Test
	public void testNegative() {
		String html = "<html><head></head><body><img id='test' alt='ONE' src='ONE'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributesHaveDifferentValuesCheck check = new AttributesHaveDifferentValuesCheck("alt", "src");
		assertFalse(check.execute(element, doc));
	}
	
}
