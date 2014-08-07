package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.HasAttributeCheck;

public class HasAttributeCheckTest {

	@Test
	public void testPostitive() {
		String html = "<html><head></head><body><img id='test' alt='ONE'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasAttributeCheck check = new HasAttributeCheck("alt");
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testPostitiveEmptyString() {
		String html = "<html><head></head><body><img id='test' alt=''/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasAttributeCheck check = new HasAttributeCheck("alt");
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testNegative() {
		String html = "<html><head></head><body><img id='test' alt='ONE'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasAttributeCheck check = new HasAttributeCheck("src");
		assertFalse(check.execute(element, doc));
	}
}
