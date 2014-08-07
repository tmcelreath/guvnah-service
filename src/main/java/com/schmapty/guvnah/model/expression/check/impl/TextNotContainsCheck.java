package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TextNotContainsCheck extends Check {

	private String value;
	
	public TextNotContainsCheck(String value) {
		this.value=value;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		return !element.html().toLowerCase().contains(value.toLowerCase());
	}

	public String getValue() {
		return value;
	}
}
