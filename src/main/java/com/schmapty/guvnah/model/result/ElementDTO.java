package com.schmapty.guvnah.model.result;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

public class ElementDTO implements ResultDTO {
	
	private static final long serialVersionUID = 5664044770390743430L;
	
	private String value;
	private Integer lineNumber;
	private Integer columnNumber;
	private String tagName;
	private String url;
	private Map<String, String> attrs;

	public ElementDTO() {
		attrs = new HashMap<String, String>();
	}
	
	public ElementDTO(Element e) {
		this();
		String eVal = e.toString();
		if(eVal.contains(">")) {
			eVal = eVal.substring(0, eVal.indexOf(">")+1);
		} else if(eVal.length()>150) {
			eVal = eVal.substring(0, 150);
		}
		this.value = eVal;
		this.tagName = e.tagName();
		for(Attribute a: e.attributes()) {
			if(a.getKey().equalsIgnoreCase("src") || a.getKey().equalsIgnoreCase("href")) {
				attrs.put(a.getKey(), e.absUrl(a.getKey()));
			} else {
				attrs.put(a.getKey(), a.getValue());
			}
		}
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	public Integer getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String> getAttrs() {
		return attrs;
	}
	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ElementDTO)) return false;
		ElementDTO e = (ElementDTO)obj;
		if(this.getValue() != null && this.getValue().equals(e.getValue())) {
			if(
				(this.getLineNumber()==null ? NULL_INTEGER : this.getLineNumber())
					.equals(e.getLineNumber()==null ? NULL_INTEGER : e.getLineNumber())
				&&
				(this.getColumnNumber()==null ? NULL_INTEGER : this.getColumnNumber())
					.equals(e.getColumnNumber()==null ? NULL_INTEGER : e.getColumnNumber())
				&&
				(this.getUrl()==null ? NULL_STRING : this.getUrl())
					.equals(e.getUrl()==null ? NULL_STRING : e.getUrl())
			) {
				return true;
			}
		} 
		return false;
	}

	@Override
	public int compareTo(ResultDTO o) {
		if(o instanceof ElementDTO) {
			ElementDTO e = (ElementDTO)o;
			Integer thisValue = (this.getLineNumber()==null?NULL_INTEGER:this.getLineNumber()) * 1000
						+ (this.getColumnNumber()==null?NULL_INTEGER:this.getColumnNumber());
			Integer otherValue = (e.getLineNumber()==null?NULL_INTEGER : e.getLineNumber()) * 1000
					+ (e.getColumnNumber()==null?NULL_INTEGER : e.getColumnNumber());
			return thisValue.compareTo(otherValue);
		}
		return 0;
	}
}
