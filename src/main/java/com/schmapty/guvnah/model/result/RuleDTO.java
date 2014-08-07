package com.schmapty.guvnah.model.result;

import com.schmapty.guvnah.model.rule.Rule;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;

public class RuleDTO implements ResultDTO {

	private static final long serialVersionUID = -4813574151586072868L;

	private Integer ruleID;
	private String ruleName;
	private String ruleDescription;
	private String ruleSetName;
	private String ruleSetDescription;
	private String ruleSetGroupID;
	private String ruleSetGroupDescription;
	private String ruleSetSubGroupID;
	private String ruleSetSubGroupDescription;
	private Integer levelValue;
	private String level;
	private String tagName;
	
	public RuleDTO() {
	}
	
	public RuleDTO(Rule rule, RuleSetName ruleSetName) {
		this.ruleID = rule.getRuleId();
		this.ruleDescription = rule.getDescription();
		this.ruleName = rule.getName();
		this.ruleSetName = ruleSetName.value();
		this.tagName = rule.getTagName().value().toUpperCase();
		if(rule.getConfidence()!=null) {
			this.levelValue = rule.getConfidence().value();
			this.level = rule.getConfidence().description();
		}
	}

	public Integer getRuleID() {
		return ruleID;
	}
	public void setRuleID(Integer ruleID) {
		this.ruleID = ruleID;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleDescription() {
		return ruleDescription;
	}
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	public String getRuleSetName() {
		return ruleSetName;
	}
	public void setRuleSetName(String ruleSetName) {
		this.ruleSetName = ruleSetName;
	}
	public String getRuleSetDescription() {
		return ruleSetDescription;
	}
	public void setRuleSetDescription(String ruleSetDescription) {
		this.ruleSetDescription = ruleSetDescription;
	}
	public String getRuleSetGroupID() {
		return ruleSetGroupID;
	}
	public void setRuleSetGroupID(String ruleSetGroupID) {
		this.ruleSetGroupID = ruleSetGroupID;
	}
	public String getRuleSetGroupDescription() {
		return ruleSetGroupDescription;
	}
	public void setRuleSetGroupDescription(String ruleSetGroupDescription) {
		this.ruleSetGroupDescription = ruleSetGroupDescription;
	}
	public String getRuleSetSubGroupID() {
		return ruleSetSubGroupID;
	}
	public void setRuleSetSubGroupID(String ruleSetSubGroupID) {
		this.ruleSetSubGroupID = ruleSetSubGroupID;
	}
	public String getRuleSetSubGroupDescription() {
		return ruleSetSubGroupDescription;
	}
	public void setRuleSetSubGroupDescription(String ruleSetSubGroupDescription) {
		this.ruleSetSubGroupDescription = ruleSetSubGroupDescription;
	}
	public Integer getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Integer levelValue) {
		this.levelValue = levelValue;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RuleDTO)) return false;
		RuleDTO e = (RuleDTO)obj;
		if(this.getRuleID() != null && this.getRuleID().equals(e.getRuleID())) {
			if(
				(this.getRuleSetName()==null ? NULL_STRING : this.getRuleSetName())
					.equals(e.getRuleSetName()==null ? NULL_STRING : e.getRuleSetName())
				&&
				(this.getRuleSetGroupID()==null ? NULL_STRING : this.getRuleSetGroupID())
					.equals(e.getRuleSetGroupID()==null ? NULL_STRING : e.getRuleSetGroupID())
				&&
				(this.getRuleSetSubGroupID()==null ? NULL_STRING : this.getRuleSetSubGroupID())
					.equals(e.getRuleSetSubGroupID()==null ? NULL_STRING : e.getRuleSetSubGroupID())
			) {
				return true;
			}
		} 
		return false;
	}
	
	@Override
	public int compareTo(ResultDTO o) {
		if(o instanceof RuleDTO) {
			RuleDTO r = (RuleDTO)o;
			// Compare rule set names first;
			int ruleSetComp = (this.getRuleName()==null?NULL_STRING:this.getRuleName())
					.compareTo(((RuleDTO) o).getRuleSetName()==null?NULL_STRING:r.getRuleSetName());
			if(ruleSetComp!=0) {
				return ruleSetComp;
			}
			// If rule set names are equal, compare group and subgroup ids
			String thisValue = ((this.getRuleSetSubGroupID()==null?NULL_STRING:this.getRuleSetGroupID()))
						+ (this.getRuleSetSubGroupID()==null?NULL_STRING:this.getRuleSetSubGroupID());
			String otherValue = (r.getRuleSetSubGroupID()==null?NULL_STRING:r.getRuleSetGroupID())
					+ (r.getRuleSetSubGroupID()==null?NULL_INTEGER:r.getRuleSetSubGroupID());
		return thisValue.compareTo(otherValue)*-1;
		}
		return 0;
	}
	
	
	
}
