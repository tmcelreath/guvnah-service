package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeValueNotMatchesCheck extends Check {

	private String name;
	private String regex;
	
	public AttributeValueNotMatchesCheck(String name, String regex) {
		this.name=name;
		this.regex=regex;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element==null) return true;
		return element.attr(name).trim().toUpperCase().matches(regex);
	}

	public String getName() {
		return name;
	}

	public String getRegex() {
		return regex;
	}

	
}
