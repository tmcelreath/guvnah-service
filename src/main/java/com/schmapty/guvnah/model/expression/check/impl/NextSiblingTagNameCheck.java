package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NextSiblingTagNameCheck extends Check {

	Logger logger = LoggerFactory.getLogger(NextSiblingTagNameCheck.class);
	
	private TagName tagName;
	
	public NextSiblingTagNameCheck(TagName tagName) {
		this.tagName=tagName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Elements sibs = element.siblingElements();
		if(sibs.isEmpty()) return false;
		Element nextSib = sibs.get(0);
		return nextSib.tagName().equalsIgnoreCase(tagName.value());
	}

	public TagName getTagName() {
		return tagName;
	}

}
