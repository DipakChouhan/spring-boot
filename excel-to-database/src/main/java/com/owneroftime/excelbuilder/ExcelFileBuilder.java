package com.owneroftime.excelbuilder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.owneroftime.excelbuilder.constants.ExcelBuilderConstants;
import com.owneroftime.model.TableModel;

public class ExcelFileBuilder extends AbstractXlsxView{
	
	private TableModel tableModel;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// getting table data
		tableModel = (TableModel) model.get("tableModel");
		
		// fetch table name from table Model
		String tableName = tableModel.getTableName();
		
		// set header data
		response.setHeader("Content-disposition", "attachment; filename=" 
							+ tableName 
							+ ExcelBuilderConstants.XML_SPREADSHEET_FORMAT);
		
		// create a sheet with table name
		Sheet sheet = workbook.createSheet(tableName);
		
		// creating initial row
		Row header = sheet.createRow(0);
		
		// iterate through table data and populate the sheet
		int rowNum = 1;
		int flag = 0;
		for(String record:tableModel.getTableRecordsList()){
			Row row = sheet.createRow(rowNum++);
			int cellNum = 0;
			for (String s : record.split(",")) {
				if (flag == 0) {
					header.createCell(cellNum).setCellValue(tableModel.getTableInfoModelList().get(cellNum).getColumnName());
				}
				row.createCell(cellNum).setCellValue(s);
				cellNum++;
			}
			flag = 1;
		}
	}

}
