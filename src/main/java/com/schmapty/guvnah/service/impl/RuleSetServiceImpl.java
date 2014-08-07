package com.schmapty.guvnah.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.schmapty.guvnah.model.rule.impl.RuleSetImpl;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;
import com.schmapty.guvnah.service.RuleSetService;

@Service
class RuleSetServiceImpl implements RuleSetService {

	@Resource
	@Qualifier(value="ruleSetMap")
	private Map<RuleSetName, RuleSetImpl> ruleSetMap;
	
	@Override
	public RuleSetImpl getRuleSet(RuleSetName rulesetName) {
		return ruleSetMap.get(rulesetName);
	}

}
