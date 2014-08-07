package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@JsonIgnoreProperties
public class HasHeaderRowsCheck extends Check {
	
	@Override
	public boolean execute(Element element, Document root) {
		Elements rows = element.getElementsByTag(TagName.TR.value());
		if(rows.size()>1) {
			for(Element row: rows) {
				int cols = row.getElementsByTag(TagName.TD.value()).size();
				int headers = row.getElementsByTag(TagName.TH.value()).size();
				if(cols>0 && headers==1) {
					return true;
				}
			}
		}
		return false;
	}

}
