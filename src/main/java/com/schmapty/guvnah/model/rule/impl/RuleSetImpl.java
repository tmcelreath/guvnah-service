package com.schmapty.guvnah.model.rule.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schmapty.guvnah.model.rule.RuleSet;

public class RuleSetImpl implements RuleSet, InitializingBean {
	
	private String id;
	private String title;
	private String abbreviation;
	private String description;
	private String url;
	
	@Resource
	@Qualifier(value="rulesetProperties")
	private Properties rulesetProperties;	
	
	private List<RuleSetGroupImpl> ruleSetGroups;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.title = rulesetProperties.getProperty(id+".title");
		this.abbreviation = rulesetProperties.getProperty(id+".abbr");
		this.description = rulesetProperties.getProperty(id+".desc");
		this.url = rulesetProperties.getProperty(id+".url");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<RuleSetGroupImpl> getRuleSetGroups() {
		return ruleSetGroups;
	}

	public void setRuleSetGroups(List<RuleSetGroupImpl> ruleSetGroups) {
		this.ruleSetGroups = ruleSetGroups;
	}	
	
}
