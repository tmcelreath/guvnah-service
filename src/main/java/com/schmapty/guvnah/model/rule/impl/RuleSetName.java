package com.schmapty.guvnah.model.rule.impl;

public enum RuleSetName {
	
	WCAG2A("WCAG2-A"),
	WCAG2AA("WCAG2-AA"),
	WCAG2AAA("WCAG2-AAA"),
	SECTION508("508"),
	W3C("W3C"),
	LINKVERIFICATION("linkverification");
	
	private String value;
	
	RuleSetName(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
}
