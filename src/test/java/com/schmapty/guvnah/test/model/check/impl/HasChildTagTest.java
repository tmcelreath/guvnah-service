package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.HasChildTagCheck;
import com.schmapty.guvnah.model.rule.TagName;

public class HasChildTagTest {

	@Test
	public void testPostitiveSingle() {
		String html = "<html><head></head><frameset id='test' alt='ONE'><noframes></noframes></frameset></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementsByTag("frameset").get(0);
		HasChildTagCheck check = new HasChildTagCheck(TagName.NOFRAMES, false);
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testPostitiveMultiple() {
		String html = "<html><head></head><frameset id='test' alt='ONE'><noframes></noframes><noframes></noframes></frameset></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasChildTagCheck check = new HasChildTagCheck(TagName.NOFRAMES, true);
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testNegative() {
		String html = "<html><head></head><frameset id='test' alt='ONE'></frameset></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasChildTagCheck check = new HasChildTagCheck(TagName.NOFRAMES, false);
		assertFalse(check.execute(element, doc));
	}
	
	@Test
	public void testNegativeSingle() {
		String html = "<html><head></head><frameset id='test' alt='ONE'><noframes></noframes><noframes></noframes></frameset></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasChildTagCheck check = new HasChildTagCheck(TagName.NOFRAMES, false);
		assertFalse(check.execute(element, doc));
	}
	
}
