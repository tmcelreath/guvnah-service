package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@JsonIgnoreProperties
public class ReturnTrueCheck extends Check {

	@Override
	public boolean execute(Element element, Document root) {
		return true;
	}

}
