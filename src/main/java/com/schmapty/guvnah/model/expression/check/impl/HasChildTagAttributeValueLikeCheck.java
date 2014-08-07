package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class HasChildTagAttributeValueLikeCheck extends Check {

	private TagName tagName;
	private String attributeName;
	private List<String> values;
	
	public HasChildTagAttributeValueLikeCheck(TagName tagName, String attributeName, List<String> attributeValues) {
		this.tagName = tagName;
		this.attributeName = attributeName;
		this.values = attributeValues;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Elements children = null;
		if(tagName.equals(TagName.ALL)) {
			children = element.getElementsByAttribute(attributeName);
		} else {
			children = element.getElementsByTag(tagName.value());
		}
		for(Element child: children) {
			for(String value: values) {
				if(child.attr(attributeName).toLowerCase().contains(value.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	public TagName getTagName() {
		return tagName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public List<String> getValues() {
		return values;
	}

}
