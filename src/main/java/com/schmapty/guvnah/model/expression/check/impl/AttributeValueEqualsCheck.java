package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeValueEqualsCheck extends Check {

	private String name;
	private String value;
	
	public AttributeValueEqualsCheck(String name, String value) {
		this.name=name;
		this.value=value;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element==null) return true;
		return element.attr(name).trim().equalsIgnoreCase(value);
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	
}
