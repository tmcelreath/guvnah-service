package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class AttributeValidValuesCheck extends Check {

	private String attributeName;
	private List<String> validValues;
	
	public AttributeValidValuesCheck(String attributeName, List<String> validValues) {
		this.attributeName = attributeName;
		this.validValues = validValues;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		if(!element.hasAttr(attributeName)) {
			return true;
		}
		String attributeValue = null;
		Attributes attributes = element.attributes();
		for(Attribute attribute: attributes) {
			if(attribute.getKey().equalsIgnoreCase(attributeName)) {
				/*
				 * This whole rigamorole is to get the actual string attribute value when 
				 * the value is an escape character like "&nbsp;". 
				 * The element.attr(name) function will tranlate the special charater.
				 * What we're doing here is getting the entire attribute html ("alt='&nbsp;'" for example)
				 * and stripping away everything but the value.
				 */
				String attributeStr = attribute.html();
				attributeStr = attributeStr.substring(attributeName.length());
				attributeStr = attributeStr.trim();
				attributeStr = attributeStr.substring(1);
				attributeStr = attributeStr.trim();
				attributeStr = attributeStr.substring(1);
				attributeStr = attributeStr.substring(0, attributeStr.length()-1);
				attributeValue = attributeStr;
				break;
			}
		}
		for(String validValue: validValues) {
			if(validValue.equalsIgnoreCase(attributeValue)) {
				return true;
			}
		}
		return false;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public List<String> getValidValues() {
		return validValues;
	}

}
