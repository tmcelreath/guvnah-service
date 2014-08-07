package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HasParentTagCheck extends Check {

	private TagName tagName;
	
	public HasParentTagCheck(TagName tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Element parent = element.parent();
		return parent != null && parent.tagName().equals(tagName.value());
	}

	public TagName getTagName() {
		return tagName;
	}

}
