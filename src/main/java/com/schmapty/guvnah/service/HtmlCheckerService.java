package com.schmapty.guvnah.service;

import com.schmapty.guvnah.web.controller.CheckRequest;
import com.schmapty.guvnah.web.controller.CheckResponse;

public interface HtmlCheckerService {

	public CheckResponse checkHtml(CheckRequest request);

}
