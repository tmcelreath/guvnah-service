package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChildTagHasAttributeCheck extends Check {

	private TagName tagName;
	private String attributeName;
	
	public ChildTagHasAttributeCheck(TagName tagName, String attributeName) {
		this.tagName = tagName;
		this.attributeName = attributeName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Elements children = element.getElementsByTag(tagName.value());
		for(Element child: children) {
			if(!child.hasAttr(attributeName) || child.attr(attributeName).trim().equals("")) {
				return false;
			}
		}
		return true;
	}

	public TagName getTagName() {
		return tagName;
	}

	public String getAttributeName() {
		return attributeName;
	}


}
