package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;
import com.schmapty.guvnah.util.ElementUtils;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;

import java.util.Map;

@JsonIgnoreProperties
public class LanguageCodeCheck extends Check {

	private Map<String, String> languageCodeMap;
	
	public LanguageCodeCheck() {
	}
	
	public LanguageCodeCheck(Map<String, String> languageCodeMap) {
		this.languageCodeMap = languageCodeMap;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		
		if(element.tagName().equalsIgnoreCase(TagName.HTML.value())) {
			
			String langCode = element.attr("lang");
			String xmlLangCode = null;
			
			boolean isXHTML = false;
			DocumentType docType = ElementUtils.getDocumentType(root);
			if(docType !=null && docType.attr("publicid").indexOf("XHTML")>-1) {
				isXHTML = true;
				xmlLangCode=element.attr("xml:lang");
			}
			
			// language attributes must be equal
			if(isXHTML && !xmlLangCode.equals(langCode)) {
				return false;
			}
			// language code must be 2-3 characters
			if(langCode.indexOf("-")>0) {
				langCode = langCode.substring(0, langCode.indexOf("-"));
			}
			if(langCode.indexOf("_")>0) {
				langCode = langCode.substring(0, langCode.indexOf("_"));
			}
			String language = languageCodeMap.get(langCode.toLowerCase());
			if(language==null) {
				return false;
			}
			return true;
		} else {
			return true;
		}
	}

}
