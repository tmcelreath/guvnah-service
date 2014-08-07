package com.schmapty.guvnah.model.rule.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;

import com.schmapty.guvnah.model.expression.Expression;
import com.schmapty.guvnah.model.expression.ExpressionParameter;
import com.schmapty.guvnah.model.rule.Confidence;
import com.schmapty.guvnah.model.rule.Rule;
import com.schmapty.guvnah.model.rule.TagName;

@JsonSerialize
public class RuleImpl implements Rule, InitializingBean {

	private Integer ruleId;
	private TagName tagName;
	private Confidence confidence;
	private Expression check;
	private String name;
	private String description;
	private RuleImpl parent;
	private List<Expression> prerequisites;

	@Resource @Qualifier(value="ruleProperties")
	private Properties ruleProperties;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		name = ruleProperties.getProperty("rule." + ruleId + ".name");
		description = ruleProperties.getProperty("rule." + ruleId + ".desc");
	}
	
	@Override
	public boolean execute(ExpressionParameter<?>... parameters) {
		if(parent==null || parent.execute(parameters)) { 
			if(prerequisites!=null) {
				for(Expression prerequisite: prerequisites) {
					if(!prerequisite.execute(parameters)) {
						return true;
					}
				}
			}
        }
        if (check.execute(parameters)) return true;
        else return false;
	}
	
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public TagName getTagName() {
		return tagName;
	}
	public void setTagName(TagName tagName) {
		this.tagName = tagName;
	}
	public Confidence getConfidence() {
		return confidence;
	}
	public void setConfidence(Confidence confidence) {
		this.confidence = confidence;
	}
	public void setCheck(Expression check) { this.check = check; }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {		
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public RuleImpl getParent() {
		return parent;
	}
	public void setParent(RuleImpl parent) {
		this.parent = parent;
	}
	public List<Expression> getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(List<Expression> prerequisites) {
		this.prerequisites = prerequisites;
	}
	public Expression getCheck() {
		return check;
	}
}
