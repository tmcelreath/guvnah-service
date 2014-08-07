package com.schmapty.guvnah.model.rule;

import com.schmapty.guvnah.model.expression.Expression;
import com.schmapty.guvnah.model.expression.ExpressionParameter;

public interface Rule {

	public boolean execute(ExpressionParameter<?>... parameters);
	public Confidence getConfidence();
	public Integer getRuleId();
	public String getName();
	public TagName getTagName();
	public String getDescription();
    public Expression getCheck();
	public void setConfidence(Confidence confidence);
	public void setRuleId(Integer ruleID);
	public void setName(String name);
	public void setTagName(TagName tagName);
	public void setDescription(String descriptions);
    public void setCheck(Expression check);

}
