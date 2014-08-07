package com.schmapty.guvnah.service;

import java.util.List;

import org.jsoup.nodes.Document;

import com.schmapty.guvnah.model.result.Result;
import com.schmapty.guvnah.model.rule.Confidence;

public interface W3CValidatorService {

	public List<Result> validate(Document document, Confidence maxConfidence);
	
}
