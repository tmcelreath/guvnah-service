package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NotHasChildTagCheck extends Check {

	private TagName tagName;
	
	public NotHasChildTagCheck(TagName tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		return !(element.getElementsByTag(tagName.value()).size()>0);
	}

	public TagName getTagName() {
		return tagName;
	}

}
