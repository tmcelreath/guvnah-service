package com.schmapty.guvnah.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schmapty.guvnah.service.ExcelConverterService;
import com.schmapty.guvnah.service.HtmlCheckerService;

@Controller
@RequestMapping("/")
public class HtmlCheckerController {

	
	Logger logger = LoggerFactory.getLogger(HtmlCheckerController.class);
	
	@Autowired
	private HtmlCheckerService checkService;
	
	@Autowired
	private ExcelConverterService excelService;
	
    @RequestMapping(method = RequestMethod.GET, value = "/")
	public String root() {
		return "ROOT";
	}
	
    @RequestMapping(method = RequestMethod.GET, value = "/ping")
	public String ping() {
		return "PING!";
	}
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/checkurl")
    public @ResponseBody
    CheckResponse check_url_get(
    			HttpServletResponse response,
    			@ModelAttribute(value="form") CheckRequest request
    		) {
		
    	CheckResponse retval = getResponse(request);
		retval.setCode(HttpServletResponse.SC_OK);
		return retval;
			
	}
	
    @RequestMapping(method = RequestMethod.POST, value = "/checkurl")
    public @ResponseBody
    CheckResponse check_url_post(
    		HttpServletResponse response,
    		@ModelAttribute(value="form") CheckRequest request) {	

    	CheckResponse retval = getResponse(request);
		retval.setCode(HttpServletResponse.SC_OK);
		return retval;
	}
    

    @RequestMapping(method = RequestMethod.GET, value = "/checkurl_excel")
    public void check_url_excel(
    		HttpServletResponse response,
    		@ModelAttribute(value="form") CheckRequest request) {	

    	CheckResponse retval = getResponse(request);
		Workbook wb = excelService.convert(request, retval);
		try {
			response.setHeader("Content-disposition", "attachment; filename=results.xls");
			wb.write( response.getOutputStream() );
		} catch (IOException e) {
			logger.error("Error writing excel.", e);
		}
	}
    
    private CheckResponse getResponse(CheckRequest request) {
		request.setUrl(formatURL(request.getUrl()));	
		CheckResponse retval =  checkService.checkHtml(request);
		return retval;
    }
    
    private String formatURL(String url) {
    	url = url.replace(" ", "");
		if(!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://")) {
			url = "http://" + url;
		}
		return url;
    }

}
