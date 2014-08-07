package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.HasAssociatedLabelCheck;

public class HasAssociatedLabelCheckTest {

	@Test
	public void testPostitive() {
		String html = "<html><head></head><body><label for='test'/><input id='test' type='text'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasAssociatedLabelCheck check = new HasAssociatedLabelCheck();
		assertTrue(check.execute(element, doc));
	}
	
	@Test
	public void testPostitiveWrongType() {
		String html = "<html><head></head><body><label for='test'/><input id='test' type='other'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasAssociatedLabelCheck check = new HasAssociatedLabelCheck();
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testNegativeNoLabel() {
		String html = "<html><head></head><body><input id='test' type='text'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasAssociatedLabelCheck check = new HasAssociatedLabelCheck();
		assertFalse(check.execute(element, doc));
	}

	@Test
	public void testNegativeMultipleLabels() {
		String html = "<html><head></head><body><label for='test'/><input id='test' type='text'/><label for='test'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasAssociatedLabelCheck check = new HasAssociatedLabelCheck();
		assertFalse(check.execute(element, doc));
	}
	
	@Test
	public void testNegativeWrongId() {
		String html = "<html><head></head><body><label for='testx'/><input id='test' type='text'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		HasAssociatedLabelCheck check = new HasAssociatedLabelCheck();
		assertFalse(check.execute(element, doc));
	}
}
