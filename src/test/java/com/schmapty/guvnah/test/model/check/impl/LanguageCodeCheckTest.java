package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.LanguageCodeCheck;

import java.util.HashMap;
import java.util.Map;

public class LanguageCodeCheckTest {

    Map<String, String> languageMap = new HashMap<String, String>();

    @Before
    public void setUp() {
            languageMap.put("en", "English");
    }

	@Test
	public void testPostitiveXML() {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html lang='en' xml:lang='en'><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementsByTag("html").get(0);
		LanguageCodeCheck check = new LanguageCodeCheck(languageMap);
		assertTrue(check.execute(element, doc));
	}
	
	@Test
	public void testPostitiveHTML() {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML4.2 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html lang='en'><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementsByTag("html").get(0);
		LanguageCodeCheck check = new LanguageCodeCheck(languageMap);
		assertTrue(check.execute(element, doc));
	}
	
	@Test
	public void testNegativeXMLMissing() {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html lang='en'><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementsByTag("html").get(0);
		LanguageCodeCheck check = new LanguageCodeCheck();
		assertFalse(check.execute(element, doc));
	}
	
	@Test
	public void testNegativeHTMLMissing() {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html xml:lang='en'><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementsByTag("html").get(0);
		LanguageCodeCheck check = new LanguageCodeCheck();
		assertFalse(check.execute(element, doc));
	}
	
	@Test
	public void testNegativeXMLBlank() {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html lang='en' xml:lang=''><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementsByTag("html").get(0);
		LanguageCodeCheck check = new LanguageCodeCheck();
		assertFalse(check.execute(element, doc));
	}	
	
	@Test
	public void testNegativeNotEqual() {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html lang='en' xml:lang='fr'><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementsByTag("html").get(0);
		LanguageCodeCheck check = new LanguageCodeCheck();
		assertFalse(check.execute(element, doc));
	}	
	
	@Test
	public void testNegativeHTMLBlank() {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html lang='' xml:lang='en'><head></head><body></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementsByTag("html").get(0);
		LanguageCodeCheck check = new LanguageCodeCheck();
		assertFalse(check.execute(element, doc));
	}

}
