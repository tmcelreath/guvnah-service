package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.ImageMapHasTextLinkEquivalentsCheck;

public class ImageMapHasTextLinkEquivalentsCheckTest {

	@Test
	public void testPostitive() {
		String html = "<html><head></head><body><img id='test' usemap map='#map'/><map name='map'><area href='href1' alt='value1'/><area href='href2' alt='value2'/><map></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		ImageMapHasTextLinkEquivalentsCheck check = new ImageMapHasTextLinkEquivalentsCheck();
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testPostitiveNoMap() {
		String html = "<html><head></head><body><img id='test' usemap map='#map'/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		ImageMapHasTextLinkEquivalentsCheck check = new ImageMapHasTextLinkEquivalentsCheck();
		assertTrue(check.execute(element, doc));
	}

	@Test
	public void testNegativeMissingAlt() {
		String html = "<html><head></head><body><img id='test' usemap map='#map'/><map name='map'><area href='href1' alt='value1'/><area href='href2'/><map></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		ImageMapHasTextLinkEquivalentsCheck check = new ImageMapHasTextLinkEquivalentsCheck();
		assertFalse(check.execute(element, doc));
	}	
	
	@Test
	public void testNegativeEmptyAlt() {
		String html = "<html><head></head><body><img id='test' usemap map='#map'/><map name='map'><area href='href1' alt='value1'/><area href='href2' alt=''/><map></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		ImageMapHasTextLinkEquivalentsCheck check = new ImageMapHasTextLinkEquivalentsCheck();
		assertFalse(check.execute(element, doc));
	}
}
