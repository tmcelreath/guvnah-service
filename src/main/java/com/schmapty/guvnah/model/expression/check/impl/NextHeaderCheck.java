package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@JsonIgnoreProperties
public class NextHeaderCheck  extends Check {

	private static final String REGEX_HEADER_TAG = "h[1-5]";
	
	@Override
	public boolean execute(Element element, Document root) {
		String tagName = element.tagName();
		if(tagName.toLowerCase().matches(REGEX_HEADER_TAG)) {
			Integer currentHeaderIndex = new Integer(tagName.substring(1));
			Elements elements = root.getAllElements();
			boolean canCheck = false;
			for(Element e: elements) {
				if(canCheck) {
					if(e.tagName().toLowerCase().matches(REGEX_HEADER_TAG)) {
						Integer nextHeaderIndex = new Integer(e.tagName().substring(1));
						return nextHeaderIndex <= currentHeaderIndex +1;
					}
				}
				if(e.equals(element)) {
					canCheck=true;
				}
			}
			return true;
		} else {
			return true; 
		}
	}

}
