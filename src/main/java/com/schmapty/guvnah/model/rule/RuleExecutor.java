package com.schmapty.guvnah.model.rule;

import java.util.concurrent.Callable;

import com.schmapty.guvnah.model.expression.ExpressionParameter;
import com.schmapty.guvnah.model.result.Result;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;

public class RuleExecutor implements Callable<Result> {

	private Rule rule;
	private ExpressionParameter<?>[] parameters;
	private RuleSetName ruleSetName;
	private RuleSetGroup ruleSetGroup;
	private RuleSetGroup ruleSetSubGroup;
	
	public RuleExecutor(Rule rule, RuleSetName ruleSetName, RuleSetGroup ruleSetGroup, RuleSetGroup ruleSetSubGroup, ExpressionParameter<?>... parameters) {
		this.rule=rule;
		this.parameters=parameters;
		this.ruleSetName=ruleSetName;
		this.ruleSetGroup=ruleSetGroup;
		this.ruleSetSubGroup=ruleSetSubGroup;
	}

	@Override
	public Result call() throws Exception {
		if(!rule.execute(parameters)) {
			return new Result(rule, ruleSetName, ruleSetGroup, ruleSetSubGroup, parameters);
		}
		return null;
	}

}
