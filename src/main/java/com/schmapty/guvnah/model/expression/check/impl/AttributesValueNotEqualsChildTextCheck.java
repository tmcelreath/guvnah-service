package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AttributesValueNotEqualsChildTextCheck extends Check {

	private String attributeName;
	private TagName tagName;
	
	public AttributesValueNotEqualsChildTextCheck(String attributeName, TagName tagName) {
		this.attributeName = attributeName;
		this.tagName = tagName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		String attributValue = element.attr(attributeName);
		Elements children = element.getElementsByTag(tagName.value());
		for(Element child: children) {
			if(child.text().trim().equalsIgnoreCase(attributValue.trim())) {
				return false;
			}
		}
		return true;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public TagName getTagName() {
		return tagName;
	}

}
