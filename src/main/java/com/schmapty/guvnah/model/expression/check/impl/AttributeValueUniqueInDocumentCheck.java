package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AttributeValueUniqueInDocumentCheck extends Check {

	private String name;
	
	public AttributeValueUniqueInDocumentCheck(String name) {
		this.name=name;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element.hasAttr(name)) {
			String value = element.attr(name);
			if(value!=null && !value.trim().equals("")) {
				Elements elements = root.getElementsByAttributeValue(name, value);
				if(elements.size()>1) {
					return false;
				}
			}
		}
		return true;
	}

	public String getName() {
		return name;
	}
}
