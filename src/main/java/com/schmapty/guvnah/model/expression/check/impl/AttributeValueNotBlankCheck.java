package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeValueNotBlankCheck extends Check {

	private String attributeName;
	
	public AttributeValueNotBlankCheck(String attributeName) {
		this.attributeName = attributeName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		return element.hasAttr(attributeName) && !element.attr(attributeName).trim().equals("");
	}

	public String getAttributeName() {
		return attributeName;
	}

}
