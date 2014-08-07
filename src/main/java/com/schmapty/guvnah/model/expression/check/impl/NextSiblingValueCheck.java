package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NextSiblingValueCheck extends Check {

	Logger logger = LoggerFactory.getLogger(NextSiblingValueCheck.class);
	
	private String value;
	
	public NextSiblingValueCheck(String value) {
		this.value=value;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		Elements sibs = element.siblingElements();
		if(sibs.isEmpty()) return false;
		Element nextSib = sibs.get(0);
		return nextSib.html().equalsIgnoreCase(value);
//				nextSib.tagName().equalsIgnoreCase(TagName.ANCHOR.value()) 
		/*
		 if (!BasicFunctions::hasAttribute('longdesc')) 
		   return true;
				else
		   return (BasicFunctions::getNextSiblingTag() == "a" && BasicFunctions::getNextSiblingInnerText() == "[d]");
		 */
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
