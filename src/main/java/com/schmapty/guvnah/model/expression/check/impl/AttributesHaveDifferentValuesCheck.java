package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributesHaveDifferentValuesCheck extends Check {

	private String attributeName1;
	private String attributeName2;
	
	public AttributesHaveDifferentValuesCheck(String attributeName1, String attributeName2) {
		this.attributeName1 = attributeName1;
		this.attributeName2 = attributeName2;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(!element.hasAttr(attributeName1)
			|| !element.hasAttr( attributeName2)) {
			return true;
		}
		return !element.attr(attributeName1).toUpperCase().trim()
				.equals(element.attr(attributeName2).toUpperCase().trim());
	}

	public String getAttributeName1() {
		return attributeName1;
	}

	public String getAttributeName2() {
		return attributeName2;
	}

}
