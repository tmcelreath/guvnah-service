package com.schmapty.guvnah.model.result;

import org.jsoup.nodes.Element;

import com.schmapty.guvnah.model.expression.ExpressionParameter;
import com.schmapty.guvnah.model.rule.Rule;
import com.schmapty.guvnah.model.rule.RuleSetGroup;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;

public class Result {
	
	private RuleDTO rule;
	private ElementDTO element;
	
	public Result(Rule rule, RuleSetName ruleSetName, RuleSetGroup ruleSetGroup, RuleSetGroup ruleSetSubGroup, ExpressionParameter<?>... parameters) {
		this.rule = new RuleDTO(rule, ruleSetName);
		this.rule.setRuleSetDescription(ruleSetGroup.getTitle());
		if(ruleSetGroup!=null) {
			this.rule.setRuleSetGroupID(ruleSetGroup.getId());
			this.rule.setRuleSetGroupDescription(ruleSetGroup.getTitle());
		}
		if(ruleSetSubGroup!=null) {
			this.rule.setRuleSetSubGroupID(ruleSetSubGroup.getId());
			this.rule.setRuleSetSubGroupDescription(ruleSetSubGroup.getTitle());
		}
		if(parameters[0].get() instanceof ElementDTO) {
			this.element = ((ElementDTO)parameters[0].get());
		} else if(parameters[0].get() instanceof Element) {
			this.element=new ElementDTO((Element)(parameters[0].get()));
		}
	}

	public ElementDTO getElement() {
		return element;
	}
	public void setElement(ElementDTO element) {
		this.element = element;
	}
	public RuleDTO getRule() {
		return rule;
	}
	public void setRule(RuleDTO rule) {
		this.rule = rule;
	}
	
}
