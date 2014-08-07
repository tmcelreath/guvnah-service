package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ElementInnerTextNotEqualsCheck extends Check {

	private String value;
	
	public ElementInnerTextNotEqualsCheck(String value) {
		this.value=value;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element==null) return true;
		return !element.text().trim().equalsIgnoreCase(value);
	}

	public String getValue() {
		return value;
	}

	
}
