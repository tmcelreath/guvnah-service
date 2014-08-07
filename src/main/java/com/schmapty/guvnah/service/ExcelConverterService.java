package com.schmapty.guvnah.service;

import org.apache.poi.ss.usermodel.Workbook;

import com.schmapty.guvnah.web.controller.CheckRequest;
import com.schmapty.guvnah.web.controller.CheckResponse;

public interface ExcelConverterService {

	public Workbook convert(CheckRequest request, CheckResponse response);
	
}
