package com.schmapty.guvnah.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schmapty.guvnah.model.rule.impl.RuleSetImpl;
import com.schmapty.guvnah.model.rule.impl.RuleSetName;
import com.schmapty.guvnah.service.RuleSetService;


@Controller
@RequestMapping(value="/ruleset")
public class RuleSetController {

	@Autowired
	private RuleSetService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/{ruleset}")
	public @ResponseBody RuleSetImpl getRuleSet(@PathVariable RuleSetName ruleset) {
		return service.getRuleSet(ruleset);
	}
}
