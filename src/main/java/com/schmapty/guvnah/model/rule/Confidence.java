package com.schmapty.guvnah.model.rule;

public enum Confidence {
	
	ERROR(0, "ERROR"), LIKELY(1, "LIKELY"), POTENTIAL(2, "POTENTIAL");
	
	private Integer value;
	private String description;
	
	Confidence(Integer value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public Integer value() {
		return value;
	}
	
	public String description() {
		return description;
	}
	
}
