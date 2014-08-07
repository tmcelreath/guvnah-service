package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties
public class HasAssociatedLabelWithTextCheck  extends Check {

	Logger logger = LoggerFactory.getLogger(HasAssociatedLabelWithTextCheck.class);
	
	@Override
	public boolean execute(Element element, Document root) {
		
		// If the element is enclosed in a label tag
		if(element.parent() != null && element.parent().tagName() != null
				&& element.parent().tagName().equals(TagName.LABEL.value())) {
			
			return element.parent().text().length()>0;
		}
		
		String id = element.id();
		// If the element doensn't have an id, the check fails
		// since we cannot associate a label.
		if(id==null || id.trim().equals("")) {
			return false;
		}
		
		Elements labels = root.getElementsByAttributeValue("for", id);
		int count = 0;
		for(Element label: labels) {
			if(label.tagName().equals(TagName.LABEL.value())) {
				String labelText = label.text().trim();
				if(labelText==null || labelText.equals("")) {
					return false;
				}
				count++;
				// More than one label for a single element is invalid. So bail if count>1.
				if(count>1) break;
			}
		}
		return count == 1;
	}

}
