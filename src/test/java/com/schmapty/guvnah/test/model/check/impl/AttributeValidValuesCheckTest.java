package com.schmapty.guvnah.test.model.check.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.AttributeValidValuesCheck;
public class AttributeValidValuesCheckTest {

	List<String> validValues = new ArrayList<String>();
	
	@Before
	public void setUp() {
		validValues.add("ONE");
		validValues.add("TWO");
	}
	
	@Test
	public void testPostitive1() {
		String html = "<html><head></head><body><img id='test' alt='ONE'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributeValidValuesCheck check = new AttributeValidValuesCheck("alt", validValues);
		assertTrue(check.execute(element, doc));
	}
	
	@Test
	public void testPostitive2() {
		String html = "<html><head></head><body><img id='test' alt='TWO'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributeValidValuesCheck check = new AttributeValidValuesCheck("alt", validValues);
		assertTrue(check.execute(element, doc));
	}
	
	
	@Test
	public void testNegative() {
		String html = "<html><head></head><body><img id='test' alt='THREE'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		AttributeValidValuesCheck check = new AttributeValidValuesCheck("alt", validValues);
		assertFalse(check.execute(element, doc));
	}

}
