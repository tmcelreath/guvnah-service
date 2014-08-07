package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HasInvalidParentTagCheck extends Check {

	private TagName parentTagName;
	
	public HasInvalidParentTagCheck(TagName parentTagName) {
		this.parentTagName = parentTagName;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Element parent = element.parent();
		if(parent.tagName().equals(parentTagName.value())) {
			return false;
		}
		return true;
	}

	public TagName getParentTagName() {
		return parentTagName;
	}

}
