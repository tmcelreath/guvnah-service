package com.schmapty.guvnah.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.schmapty.guvnah.model.result.ElementDTO;
import com.schmapty.guvnah.model.result.ElementResultDTO;
import com.schmapty.guvnah.model.result.RuleDTO;
import com.schmapty.guvnah.model.result.RuleResultDTO;
import com.schmapty.guvnah.model.result.SortedResultDTO;
import com.schmapty.guvnah.service.ExcelConverterService;
import com.schmapty.guvnah.web.controller.CheckRequest;
import com.schmapty.guvnah.web.controller.CheckResponse;

@Service
public class ExcelConverterServiceImpl implements ExcelConverterService {
	
	public static final int MAX_CELL_LENGTH = 32767;
	
	@Override
	public Workbook convert(CheckRequest request, CheckResponse response) {
		Workbook retval = new HSSFWorkbook();
		Map<String, CellStyle> styles = createStyles(retval);
		Sheet requestSheet = retval.createSheet("request");
		
		Row dateRow = requestSheet.createRow(0);
		dateRow.createCell(0).setCellValue("DATE");
		dateRow.getCell(0).setCellStyle(styles.get("header"));
		dateRow.createCell(1).setCellValue(new Date());
		dateRow.getCell(1).setCellStyle(styles.get("date"));
		
		Row urlrow = requestSheet.createRow(1);
		urlrow.createCell(0).setCellValue("URL");
		urlrow.getCell(0).setCellStyle(styles.get("header"));
		urlrow.createCell(1).setCellValue(request.getUrl());
		
		Row rulesetRow = requestSheet.createRow(2);
		rulesetRow.createCell(0).setCellValue("RULE SETS");
		rulesetRow.getCell(0).setCellStyle(styles.get("header"));
		for(int i=0;i<request.getRuleset().length;i++) {
			rulesetRow.createCell(i+1).setCellValue(request.getRuleset()[i].value());
		}
		rulesetRow.getCell(0).setCellStyle(styles.get("header"));
		
		Row levelRow = requestSheet.createRow(3);
		levelRow.createCell(0).setCellValue("LEVEL");
		levelRow.getCell(0).setCellStyle(styles.get("header"));
		levelRow.createCell(1).setCellValue(request.getLevel().name());
		
		Row platformRow = requestSheet.createRow(4);
		platformRow.createCell(0).setCellValue("PLATFORM");
		platformRow.getCell(0).setCellStyle(styles.get("header"));
		platformRow.createCell(1).setCellValue(request.getPlatform().value());
		
		Row executeJSRow = requestSheet.createRow(5);
		executeJSRow.createCell(0).setCellValue("RUS SCRIPTS?");
		executeJSRow.getCell(0).setCellStyle(styles.get("header"));
		executeJSRow.createCell(1).setCellValue(request.getExecutejs());
		
		Sheet sheet = retval.createSheet("results");

		short rownum = 0;
		Row header = sheet.createRow(rownum);
		writeHeader(header, styles.get("header"));
		rownum++;
		
        for(SortedResultDTO result: response.getResults()) {
        	if(result instanceof ElementResultDTO) {
        		for(RuleDTO rule: ((ElementResultDTO)result).getValue()) {
            		Row row = sheet.createRow(rownum);
            		writeRow(row, rule, ((ElementResultDTO)result).getKey());
                	rownum++;
        		}
        	} else if(result instanceof RuleResultDTO) {
        		for(ElementDTO element: ((RuleResultDTO)result).getValue()) {
        			Row row = sheet.createRow(rownum);
        			writeRow(row, ((RuleResultDTO)result).getKey(), element);
                	rownum++;
        		}
        	}
        }
        
        Sheet htmlSheet = retval.createSheet("html");
        rownum = 1;
        for(int i = 0; i< response.getHtml().size(); i++) {
        	String line = response.getHtml().get(i).trim();
        	if(line.length()>MAX_CELL_LENGTH) {
        		line = line.substring(0, MAX_CELL_LENGTH);
        	}
        	htmlSheet.createRow(i).createCell(0).setCellValue(line);
        }
        
        return retval;
	}

	public void writeHeader(Row row, CellStyle style) {
			row.createCell(0).setCellValue("TAG NAME");
			row.getCell(0).setCellStyle(style);
			row.createCell(1).setCellValue("LINE");
			row.getCell(1).setCellStyle(style);
			row.createCell(2).setCellValue("COLUMN");
			row.getCell(2).setCellStyle(style);
			row.createCell(3).setCellValue("ELEMENT");
			row.getCell(3).setCellStyle(style);
			row.createCell(4).setCellValue("RULE NAME");
			row.getCell(4).setCellStyle(style);
			row.createCell(5).setCellValue("RULE SET NAME");
			row.getCell(5).setCellStyle(style);
			row.createCell(6).setCellValue("RULE SET GROUP NAME");
			row.getCell(6).setCellStyle(style);
			row.createCell(7).setCellValue("RULE SET SUBGROUP NAME");
			row.getCell(7).setCellStyle(style);
			row.createCell(8).setCellValue("DESCRIPTION");
			row.getCell(8).setCellStyle(style);
	}
	
	private void writeRow(Row row, RuleDTO rule, ElementDTO element)  {
		row.createCell(0).setCellValue(element.getTagName());
		row.createCell(1).setCellValue(element.getLineNumber()==null?0:element.getLineNumber());
		row.createCell(2).setCellValue(element.getColumnNumber()==null?0:element.getColumnNumber());
		row.createCell(3).setCellValue(element.getValue());
		row.createCell(4).setCellValue(rule.getRuleName());
		row.createCell(5).setCellValue(rule.getRuleSetName());
		row.createCell(6).setCellValue(rule.getRuleSetGroupDescription());
		row.createCell(7).setCellValue(rule.getRuleSetSubGroupDescription());
		row.createCell(8).setCellValue(rule.getRuleDescription());
	}

	
    /**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(false);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula_2", style);
        
        style = wb.createCellStyle();
        style.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        styles.put("date", style);
        
        return styles;
    }

}
