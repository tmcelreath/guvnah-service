package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeValueBlankCheck extends Check {

	private String attributeName;
	
	public AttributeValueBlankCheck(String attributeName) {
		this.attributeName = attributeName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		return element.hasAttr(attributeName) && element.attr(attributeName).trim().equals("");
	}

	public String getAttributeName() {
		return attributeName;
	}

}
