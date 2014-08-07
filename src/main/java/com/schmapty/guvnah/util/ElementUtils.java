package com.schmapty.guvnah.util;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.schmapty.guvnah.model.rule.TagName;

public class ElementUtils {

	static Logger logger = LoggerFactory.getLogger(ElementUtils.class);
	
	public static Integer getSizeAttribute(Element element, String attributeName) {
		String valueStr =  element.attr(attributeName).trim();
		
		if(valueStr.endsWith("%")) return 0;
		
		valueStr = valueStr.replace("px","");
		
		// CNN had an image that was 75.6px, which makes no sense, but now
		// we have to accout for 'partial pixels'.
		boolean increment = false;
		if(valueStr.contains("."))  {
			// round up to the next integer
			increment=true;
			valueStr=valueStr.substring(0, valueStr.indexOf("."));
		}
		if(valueStr.trim().equals("")) return 0;
		Integer retval = null;
		try {
			retval = new Integer(valueStr);
			if(increment) {
				retval++;
			}
		} catch (Exception e) {
			logger.error("Could not convert: " + valueStr + " : " + element);
			retval=0;
		}
		return retval;
	}
	
	public static DocumentType getDocumentType(Document document) {
		List<Node>nods = document.childNodes();
        for (Node node : nods) {
           if (node instanceof DocumentType) {
        	   return (DocumentType)node;
           }
       }
       return null;
	}
	
	public static String getDocumentLanguage(Document document) {
		String lang = "en";
		Elements htmlTags = document.getElementsByTag(TagName.HTML.value());
		if(htmlTags.size()>0) {
			String htmlLang = htmlTags.get(0).attr("lang").trim().toLowerCase();
			if(!htmlLang.trim().equals("")) {
				lang = htmlLang;
			}
		}
		return lang;
	}

}
