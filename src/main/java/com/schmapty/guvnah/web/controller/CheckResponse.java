package com.schmapty.guvnah.web.controller;

import java.util.List;

import com.schmapty.guvnah.model.result.SortedResultDTO;

public class CheckResponse {

	private Integer code;
	private String message;
	private List<SortedResultDTO> results;
	private Integer numberOfResults;
	private Integer numberOfElements;
	private Integer numberOfRules;
	private List<String> html;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SortedResultDTO> getResults() {
		return results;
	}
	public void setResults(List<SortedResultDTO> results) {
		this.results = results;
	}
	
	public Integer getNumberOfResults() {
		return numberOfResults;
	}
	public void setNumberOfResults(Integer numberOfResults) {
		this.numberOfResults = numberOfResults;
	}
	public Integer getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public Integer getNumberOfRules() {
		return numberOfRules;
	}
	public void setNumberOfRules(Integer numberOfRules) {
		this.numberOfRules = numberOfRules;
	}
	public List<String> getHtml() {
		return html;
	}
	public void setHtml(List<String> html) {
		this.html = html;
	}
	
	
}
