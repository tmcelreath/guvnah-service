package com.schmapty.guvnah.web.controller;

import com.schmapty.guvnah.model.rule.Confidence;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;
import com.schmapty.guvnah.service.SortOrder;
import com.schmapty.guvnah.service.UserAgent;

public class CheckRequest {

	private RuleSetName[] ruleset;
	private String url;
	private Confidence level;
	private UserAgent platform;
	private SortOrder sort;
	private String username;
	private String password;
	private Boolean outputAsExcel = Boolean.FALSE;
	private Boolean executejs = Boolean.FALSE;
	
	public RuleSetName[] getRuleset() {
		return ruleset;
	}
	public void setRuleset(RuleSetName[] ruleset) {
		this.ruleset = ruleset;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Confidence getLevel() {
		return level;
	}
	public void setLevel(Confidence level) {
		this.level = level;
	}
	public UserAgent getPlatform() {
		return platform;
	}
	public void setPlatform(UserAgent platform) {
		this.platform = platform;
	}
	public SortOrder getSort() {
		return sort;
	}
	public void setSort(SortOrder sort) {
		this.sort = sort;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getOutputAsExcel() {
		return outputAsExcel;
	}
	public void setOutputAsExcel(Boolean outputAsExcel) {
		this.outputAsExcel = outputAsExcel;
	}
	public Boolean getExecutejs() {
		return executejs;
	}
	public void setExecutejs(Boolean executejs) {
		this.executejs = executejs;
	}
	
	
}
