package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.util.ElementUtils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeMaxLengthCheck extends Check {

	private String attributeName;
	private Integer maxLength;
	
	public AttributeMaxLengthCheck(String attributeName, Integer maxLength) {
		this.attributeName = attributeName;
		this.maxLength=maxLength;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		int length = maxLength;
		// Vary the max length based on the doc language code
		String lang = ElementUtils.getDocumentLanguage(root);
		if(lang.equals("ger") || lang.equals("de")) {
			length += 15;
		} else if (lang.equals("kor") || lang.equals("ko")) {
			length -= 10;
		}
		return element.attr(attributeName).length()<=length;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

}
