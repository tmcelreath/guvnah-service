package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.util.ElementUtils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeMinValuePixelsCheck extends Check {
	
	private String name;
	private Integer minValue;
	
	public AttributeMinValuePixelsCheck(String name, Integer minValue) {
		this.name=name;
		this.minValue=minValue;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Integer value = ElementUtils.getSizeAttribute(element, name);
		return value>=minValue;
	}

	public String getName() {
		return name;
	}

	public Integer getMinValue() {
		return minValue;
	}
	
}
