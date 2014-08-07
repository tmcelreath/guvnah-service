package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class HasAttributeCheck  extends Check {

	private List<String> attributeNames;

	public HasAttributeCheck(String attributeName) {
        this.attributeNames = new ArrayList<String>();
		this.attributeNames.add(attributeName);
	}

    public HasAttributeCheck(List<String> attributeNames) {
        this.attributeNames = attributeNames;
    }

	@Override
	public boolean execute(Element element, Document root) {
        for(String name: attributeNames) {
		    if(element.hasAttr(name)) {
                return true;
            }
        }
        return false;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}

}
