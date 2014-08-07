package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class NotHasAttributesCheck extends Check {

	private List<String> attributeNames;

	public NotHasAttributesCheck(List<String> attributeNames) {
		this.attributeNames = attributeNames;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		for(String name: attributeNames) {
			if(element.hasAttr(name)) {
				return false;
			}
		}
		return true;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}

}
