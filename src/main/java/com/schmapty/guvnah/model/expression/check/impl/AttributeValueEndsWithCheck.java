package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeValueEndsWithCheck extends Check {

	private String name;
	private String value;
	
	public AttributeValueEndsWithCheck(String name, String value) {
		this.name=name;
		this.value=value;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element==null || value==null) return false;
		return element.attr(name).toLowerCase().trim().endsWith(value.toLowerCase());
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	
}
