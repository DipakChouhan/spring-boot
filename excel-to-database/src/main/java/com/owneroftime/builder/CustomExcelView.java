package com.owneroftime.builder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.owneroftime.databean.ExcelDatabaseDataBean;

public class CustomExcelView extends AbstractXlsxView{
	
	private ExcelDatabaseDataBean excelDbDataBean;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		response.setHeader("Content-Disposition", "attachment;filename=\"student.xls\"");
//		response.setHeader("Content-disposition", "attachment; filename=" + filename);
		excelDbDataBean = (ExcelDatabaseDataBean) model.get("excelDbDataBean");
		
	}

}
