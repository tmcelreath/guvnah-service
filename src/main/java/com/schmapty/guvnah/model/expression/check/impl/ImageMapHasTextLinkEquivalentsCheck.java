package com.schmapty.guvnah.model.expression.check.impl;

import com.schmapty.guvnah.model.expression.check.Check;
import com.schmapty.guvnah.model.rule.TagName;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@JsonIgnoreProperties
public class ImageMapHasTextLinkEquivalentsCheck extends Check {

	@Override
	public boolean execute(Element element, Document root) {
		
		if(!element.hasAttr("usemap")) {
			return true;
		}
		String mapName = element.attr("map");
		if(!mapName.equals("")) {
			mapName = mapName.substring(1); // remove leading '#'
			Elements maps = root.getElementsByTag("map");
			Element map = null;
			for(Element m: maps) {
				if(m.attr("name").equalsIgnoreCase(mapName)) {
					map = m;
					break;
				}
			}
			if(map==null) {
				return true;
			}
			Elements areas = map.getElementsByTag(TagName.AREA.value());
			for(Element area: areas) {
				if(area.hasAttr("href")) {
					if(area.attr("alt").trim().equals("")) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
