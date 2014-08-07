package com.schmapty.guvnah.test.model.check.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;

import com.schmapty.guvnah.model.expression.check.impl.NextHeaderCheck;

public class NextHeaderCheckTest {

	@Test
	public void testPostitive_H1_H2() {
		String html = "<html><head></head><body><h1 id='test'/><h2/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		NextHeaderCheck check = new NextHeaderCheck();
		assertTrue(check.execute(element, doc));
	}
	
	
	@Test
	public void testPostitive_H1_H1() {
		String html = "<html><head></head><body><h1 id='test'/><h1/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		NextHeaderCheck check = new NextHeaderCheck();
		assertTrue(check.execute(element, doc));
	}
	
	@Test
	public void testPostitive_H2_H1() {
		String html = "<html><head></head><body><h2 id='test'/><h1/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		NextHeaderCheck check = new NextHeaderCheck();
		assertTrue(check.execute(element, doc));
	}
	
	@Test
	public void testPostitive_H3_H1() {
		String html = "<html><head></head><body><h3 id='test'/><h1/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		NextHeaderCheck check = new NextHeaderCheck();
		assertTrue(check.execute(element, doc));
	}

	
	@Test
	public void testNegative_H1_H3() {
		String html = "<html><head></head><body><h1 id='test'/><h3/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		NextHeaderCheck check = new NextHeaderCheck();
		assertFalse(check.execute(element, doc));
	}
	
	@Test
	public void testNegative_H2_H5() {
		String html = "<html><head></head><body><h2 id='test'/><h5/></body></html>";
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById("test");
		NextHeaderCheck check = new NextHeaderCheck();
		assertFalse(check.execute(element, doc));
	}
}
