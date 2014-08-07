package com.schmapty.guvnah.model.rule;

import java.util.HashMap;
import java.util.Map;

public enum TagName {
	ALL("*"),
	ANCHOR("a"), 
	APPLET("applet"),
	AREA("area"),
	B("b"),
	BLINK("blink"),
	BODY("body"), 
	CAPTION("caption"),
	COL("col"),
	COLGROUP("colgroup"),
	EM("em"),
	EMBED("embed"),
	FIELDSET("fieldset"),
	FONT("font"),
	FORM("form"),
	FRAME("frame"), 
	FRAMESET("frameset"), 
	H1("h1"),
	H2("h2"),
	H3("h3"),
	H4("h4"),
	H5("h5"),
	HEAD("head"),
	HTML("html"), 
	I("i"),
	IMG("img"), 
	INPUT("input"),
	LABEL("label"),
	MARQUEE("marquee"),
	META("meta"),	
	NOEMBED("noembed"),
	NOFRAMES("noframes"),
	NOSCRIPT("noscript"),
	OBJECT("OBJECT"),
	P("p"),
	PRE("pre"),
	SCRIPT("script"),
	SELECT("select"),
	STRONG("strong"),
	TABLE("table"),
	TBODY("tbody"),
	TD("td"),
	TEXTAREA("textarea"),
	TFOOT("tfoot"),
	TH("th"),
	THEAD("thead"),
	TITLE("title"),
	TR("tr"),
	U("u");
	
	private String value;
	private static Map<String, TagName> map = new HashMap<String, TagName>();
	
	static {
		for (TagName t :TagName.values()) {
			map.put(t.value, t);
		}
	}
	
	TagName(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
	public static TagName lookup(String name) {
		return map.get(name);
	}
}
