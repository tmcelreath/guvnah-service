package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.HasInvalidParentTagCheck;
import com.schmapty.guvnah.model.rule.TagName;

public class HasInvalidParentCheckTest {

	@Test
	public void testPostitive() {
		String html = "<html><head></head><body><img id='test'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasInvalidParentTagCheck check = new HasInvalidParentTagCheck(TagName.ANCHOR);
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testNegative() {
		String html = "<html><head></head><body><a><img id='test'/></a></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasInvalidParentTagCheck check = new HasInvalidParentTagCheck(TagName.ANCHOR);
		assertFalse(check.execute(element, doc));
	}

}
