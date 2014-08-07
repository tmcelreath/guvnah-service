package com.schmapty.guvnah.model.rule.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schmapty.guvnah.model.rule.RuleSetGroup;

public class RuleSetGroupImpl implements RuleSetGroup, InitializingBean {

	private String id;
	private String title;
	private List<RuleSetGroupImpl> ruleSetGroups;
	private List<RuleImpl> rules;
	
	@Resource
	@Qualifier(value="rulesetProperties")
	private Properties rulesetProperties;	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.title = rulesetProperties.getProperty(id + ".title");
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
	public List<RuleSetGroupImpl> getRuleSetGroups() {
		return ruleSetGroups;
	}
	public void setRuleSetGroups(List<RuleSetGroupImpl> ruleSetGroups) {
		this.ruleSetGroups = ruleSetGroups;
	}
	public List<RuleImpl> getRules() {
		return rules;
	}
	public void setRules(List<RuleImpl> rules) {
		this.rules = rules;
	}	
}
