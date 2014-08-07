package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.util.ElementUtils;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@JsonIgnoreProperties
public class HasDoctypeCheck extends Check {

	
	@Override
	public boolean execute(Element element, Document root) {
		return ElementUtils.getDocumentType(root) != null;
	}

}
