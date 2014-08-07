package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class ElementInnerTextNotInCheck  extends Check {

	private List<String> values;
	
	public ElementInnerTextNotInCheck(List<String> values) {
		this.values=values;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element==null) return true;
		String text = element.text().trim();
		for(String value: values) {
			if (text.equalsIgnoreCase(value)) {
				return false;
			}
		}
		return true;
	}

	public List<String> getValues() {
		return values;
	}	
}
