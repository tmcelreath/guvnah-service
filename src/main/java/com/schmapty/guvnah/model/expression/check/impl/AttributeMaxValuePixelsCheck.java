package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.util.ElementUtils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeMaxValuePixelsCheck extends Check {
	
	private String name;
	private Integer maxValue;
	
	public AttributeMaxValuePixelsCheck(String name, Integer maxValue) {
		this.name=name;
		this.maxValue=maxValue;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Integer value = ElementUtils.getSizeAttribute(element, name);
		return value<=maxValue;
	}

	public String getName() {
		return name;
	}

	public Integer getMaxValue() {
		return maxValue;
	}
	
}
