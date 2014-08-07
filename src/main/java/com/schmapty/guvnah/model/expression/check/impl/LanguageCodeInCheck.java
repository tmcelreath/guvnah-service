package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class LanguageCodeInCheck extends Check {

	private List<String> codes;
	
	public LanguageCodeInCheck(List<String> codes) {
		this.codes=codes;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		
		String lang = null;
		Elements elements = root.getElementsByTag(TagName.HTML.value());
		if(elements.size()>0) {
			Element html = elements.get(0);
			lang = html.hasAttr("lang") ? html.attr("lang") : html.attr("xml:lang");
		}
		
		if(lang!=null) {
			for(String code: codes) {
				if(code.equalsIgnoreCase(lang)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<String> getCodes() {
		return codes;
	}

}
