package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class HasNoChildTagInArrayCheck extends Check {

	private List<TagName> tagNames;
	
	public HasNoChildTagInArrayCheck(List<TagName> tagNames) {
		this.tagNames = tagNames;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		for(TagName tagName: tagNames) {
			Elements children = element.getElementsByTag(tagName.value());
			if(children.size()>0) {
				return false;
			}
		}
		return true;
	}

	public List<TagName> getTagNames() {
		return tagNames;
	}

}
