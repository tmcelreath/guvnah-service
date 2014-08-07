package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class AttributeValueNotInCheck extends Check {
	
	private String name;
	private List<String> values;
	
	public AttributeValueNotInCheck(String name, List<String> values) {
		this.name=name;
		this.values=values;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element==null) return false;
		String value = element.attr(name);
		for(String s: values) {
			//fuzzy match
			if(s.startsWith("%") && s.endsWith("%")) {
				if(value.contains(s.substring(1,s.length()-1))) {
					return false;
				}
			}
			// exact match
			else if(value.equalsIgnoreCase(s)) {
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
