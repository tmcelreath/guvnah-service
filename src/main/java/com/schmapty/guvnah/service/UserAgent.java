package com.schmapty.guvnah.service;

public enum UserAgent {
	
	DESKTOP("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:25.0) Gecko/20100101 Firefox/25.0"), 
	MOBILE( "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3");
	
	private String value;
	
	UserAgent(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
}
