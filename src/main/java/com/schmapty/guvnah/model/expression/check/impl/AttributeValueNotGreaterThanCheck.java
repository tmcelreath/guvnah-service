package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AttributeValueNotGreaterThanCheck extends Check {

	private String name;
	private Integer value;
	
	public AttributeValueNotGreaterThanCheck(String name, Integer value) {
		this.name=name;
		this.value=value;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		String val = element.attr(name).replace("%", "").replace("px", "").trim();
		Float valNum = null;
		try {
			valNum = new Float(val);
		} catch (Exception e) {
			// Not a number. Exit rule.
			return true;
		}
		
		return valNum <= value;
	}

	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}

}
