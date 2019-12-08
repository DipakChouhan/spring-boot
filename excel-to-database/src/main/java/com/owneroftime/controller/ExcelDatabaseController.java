package com.owneroftime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.owneroftime.databean.ExcelDatabaseDataBean;
import com.owneroftime.services.ExcelDatabaseService;

@Controller
@RequestMapping(value = "/excel-db/")
public class ExcelDatabaseController {
	
	@Autowired
	ExcelDatabaseDataBean excelDbDataBean;
	@Autowired
	ExcelDatabaseService excelDbService;
	
	/**
	 * This method with open the page the
	 * @return String : upload-download.jsp
	 */
	@RequestMapping(value = "/download-table")
	public String downloadTable(ModelMap model) {
		excelDbDataBean.setDownload(true);
		excelDbDataBean.setUpload(false);
		if (excelDbDataBean.getTablesList().size() == 0) {
			excelDbDataBean.getTablesList().add("TODO");
			excelDbDataBean.getTablesList().add("MASTER");
			excelDbDataBean.getTablesList().add("SLAVE_DATA");
		}
		model.addAttribute("excelDbDataBean", excelDbDataBean);
		return "uploadDownload";
	}
	
	@RequestMapping(value = "/download-table/xsls-report")
	public String downloadTableExcelFormat(@ModelAttribute("excelDbDataBean") ExcelDatabaseDataBean bean) {
		excelDbDataBean.getDownUpModel().setDownloadTableName(bean.getDownUpModel().getDownloadTableName());
		excelDbService.getAllRecords();
		System.out.println(bean.getDownUpModel().getDownloadTableName());
//		return new ModelAndView(new CustomExcelView(), "excelDbDataBean", excelDbDataBean);
		return "uploadDownload";
	}
	
	@RequestMapping(value = "/upload-table")
	public String uploadTable(ModelMap model) {
		excelDbDataBean.setDownload(false);
		excelDbDataBean.setUpload(true);
		model.addAttribute("excelDbDataBean", excelDbDataBean);
		return "uploadDownload";
	}
}
