package com.schmapty.guvnah.test.model.rule;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String match = "text=window.onload text";
		System.out.println(match.matches(".*window\\.onload.*"));

	}

}
