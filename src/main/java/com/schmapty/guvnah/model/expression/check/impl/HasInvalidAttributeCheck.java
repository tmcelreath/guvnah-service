package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HasInvalidAttributeCheck extends Check {

	private String attributeName;
	private String prerequisiteAttributeName;
	private String prerequisiteAttributeValue;
	
	public HasInvalidAttributeCheck(String attributeName) {
		this(attributeName, null, null);
	}
	
	public HasInvalidAttributeCheck(String attributeName, String prerequisiteAttributeName) {
		this(attributeName, prerequisiteAttributeName, null);
	}
	
	public HasInvalidAttributeCheck(String attributeName, String prerequisiteAttributeName, String prerequisiteAttributeValue) {
		this.attributeName = attributeName;
		this.prerequisiteAttributeName = prerequisiteAttributeName;
		this.prerequisiteAttributeValue = prerequisiteAttributeValue;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(prerequisiteAttributeName!=null) {
			if(!element.hasAttr(prerequisiteAttributeName)) {
				return true;
			} else if(prerequisiteAttributeValue != null) {
				if(prerequisiteAttributeValue.startsWith("[!]")) {
					
				} else if(!element.attr(prerequisiteAttributeName).trim()
						.equalsIgnoreCase(prerequisiteAttributeValue)) {
					return true;
				}
			}
		}
		return !element.hasAttr(attributeName);
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getPrerequisiteAttributeName() {
		return prerequisiteAttributeName;
	}

	public String getPrerequisiteAttributeValue() {
		return prerequisiteAttributeValue;
	}

}
