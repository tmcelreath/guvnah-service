package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class FieldsetGroupCheck  extends Check {

	private String inputType;

	public FieldsetGroupCheck(String inputType) {
		this.inputType = inputType;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		
		// First, verify that the element is a FORM.
		if(!element.tagName().equalsIgnoreCase(TagName.FORM.value())) {
			return true;
		}
		
		List<String> values = new ArrayList<String>();
		Elements inputs = element.getElementsByAttributeValue("type", inputType);
		for(Element input: inputs) {
			if(input.tagName().equalsIgnoreCase(TagName.INPUT.value())) {
				if(values.contains(input.attr("value"))) {
					if(!input.parent().tagName().equalsIgnoreCase(TagName.FIELDSET.value())) {
						return false;
					}
				} else {
					if(input.hasAttr("value")) {
						values.add(input.attr("value"));
					}
				}
			}
		}
		return true;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
}
