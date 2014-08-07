package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChildTagHasValueCheck extends Check {

	private TagName tagName;
	public ChildTagHasValueCheck(TagName tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Elements children = element.getElementsByTag(tagName.value());
		for(Element child: children) {
			if(child.text().trim().equals("")) {
				return false;
			}
		}
		return true;
	}

	public TagName getTagName() {
		return tagName;
	}


}
