package com.schmapty.guvnah.test.model.check.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.AttributeInvalidValuesCheck;
public class AttributeInvalidValuesCheckTest {

	List<String> invalidValues = new ArrayList<String>();
	
	@Before
	public void setUp() {
		invalidValues.add("&nbsp;");
		invalidValues.add("spacer");
	}
	
	@Test
	public void testPostitive() {
		String html = "<html><head></head><body><img id='test' alt='ONE'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributeInvalidValuesCheck check = new AttributeInvalidValuesCheck("alt", invalidValues);
		assertTrue(check.execute(element, doc));
	}
	
	@Test
	public void testFirstNegative() {
		String html = "<html><head></head><body><img id='test' alt='&nbsp;'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributeInvalidValuesCheck check = new AttributeInvalidValuesCheck("alt", invalidValues);
		assertFalse(check.execute(element, doc));
	}

	@Test
	public void testSecondNegative() {
		String html = "<html><head></head><body><img id='test' alt='spacer'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributeInvalidValuesCheck check = new AttributeInvalidValuesCheck("alt", invalidValues);
		assertFalse(check.execute(element, doc));
	}
}
