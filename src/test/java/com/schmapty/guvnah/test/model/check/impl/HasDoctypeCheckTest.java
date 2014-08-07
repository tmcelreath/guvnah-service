package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.HasDoctypeCheck;

public class HasDoctypeCheckTest {

	@Test
	public void testPostitive() {
		String html = "<!DOCTYPE html><html><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasDoctypeCheck check = new HasDoctypeCheck();
		assertTrue(check.execute(element, doc));
	}

	
	@Test
	public void testNegative() {
		String html = "<html><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasDoctypeCheck check = new HasDoctypeCheck();
		assertFalse(check.execute(element, doc));
	}
	
}
