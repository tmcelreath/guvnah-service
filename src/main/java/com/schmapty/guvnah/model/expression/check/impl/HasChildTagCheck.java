package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HasChildTagCheck extends Check {

	private TagName tagName;
	private Boolean canBeMultiple;
	
	public HasChildTagCheck(TagName tagName, Boolean canBeMultiple) {
		this.tagName = tagName;
		this.canBeMultiple = canBeMultiple;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Elements children = element.getElementsByTag(tagName.value());
		if(canBeMultiple) {
			return children.size()>0;
		} else {
			return children.size()==1;
		}
	}

	public TagName getTagName() {
		return tagName;
	}

	public Boolean getCanBeMultiple() {
		return canBeMultiple;
	}

}
