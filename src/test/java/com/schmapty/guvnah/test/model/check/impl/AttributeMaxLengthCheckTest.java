package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.AttributeMaxLengthCheck;

public class AttributeMaxLengthCheckTest {

	@Test
	public void testPostitive() {
		String html = "<html><head></head><body><img id='test' alt='123456789'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributeMaxLengthCheck check = new AttributeMaxLengthCheck("alt", 9);
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testNegative() {
		String html = "<html><head></head><body><img id='test' alt='123456789'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributeMaxLengthCheck check = new AttributeMaxLengthCheck("alt", 8);
		assertFalse(check.execute(element, doc));
	}
}
