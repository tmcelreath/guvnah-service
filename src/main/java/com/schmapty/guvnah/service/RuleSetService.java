package com.schmapty.guvnah.service;

import com.schmapty.guvnah.model.rule.impl.RuleSetImpl;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;

public interface RuleSetService {

	public RuleSetImpl getRuleSet(RuleSetName rulesetName);
	
}
