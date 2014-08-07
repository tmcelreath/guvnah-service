package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class AttributeValueNotEndsWithCheck extends Check {

	private String name;
	private List<String> values;
	
	public AttributeValueNotEndsWithCheck(String name, List<String> values) {
		this.name=name;
		this.values=values;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element==null || values==null) return true;
		String attrVal = element.attr(name).trim().toLowerCase();
		for(String value: values) {
			if(attrVal.endsWith(value.toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public List<String> getValues() {
		return values;
	}

	
}
