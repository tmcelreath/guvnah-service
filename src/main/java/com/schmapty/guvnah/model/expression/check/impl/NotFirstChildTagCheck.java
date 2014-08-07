package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NotFirstChildTagCheck extends Check {

	private TagName tagName;
	
	public NotFirstChildTagCheck(TagName tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Elements children = element.children();
		if(children.size()>0 && children.get(0).tagName().equalsIgnoreCase(tagName.value())) {
			return false;
		}
		return true;
	}

	public TagName getTagName() {
		return tagName;
	}

}
