package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.util.ElementUtils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageWithMissingLongdescCheck extends Check {

	Logger logger = LoggerFactory.getLogger(ImageWithMissingLongdescCheck.class);
	
	private Integer minWidth;
	private Integer minHeight;
	
	public ImageWithMissingLongdescCheck(Integer minWidth, Integer minHeight) {
		this.minWidth = minWidth;
		this.minHeight = minHeight;
	}
	
	@Override
	public boolean execute(Element element, Document root) {
		int width = ElementUtils.getSizeAttribute(element, "width");
		int height = ElementUtils.getSizeAttribute(element, "heigt");
		if(width<minWidth || height<minHeight) return true;
		return element.hasAttr("longdesc");

	}

	public Logger getLogger() {
		return logger;
	}

	public Integer getMinWidth() {
		return minWidth;
	}

	public Integer getMinHeight() {
		return minHeight;
	}

}
