package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ElementInnerTextMaxLengthCheck extends Check {

	private Integer maxLength;
	
	public ElementInnerTextMaxLengthCheck(Integer maxLength) {
		this.maxLength=maxLength;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(element==null) return true;
		String text = element.text();
		return text.trim().length()<=maxLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	
}
