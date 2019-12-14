package com.owneroftime.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.owneroftime.databean.ExcelDatabaseDataBean;
import com.owneroftime.excelbuilder.ExcelFileBuilder;
import com.owneroftime.model.TableModel;
import com.owneroftime.services.ExcelDatabaseService;
import com.owneroftime.validators.ExcelDatabaseValidator;

@Controller
@RequestMapping(value = "/excel-db/")
public class ExcelDatabaseController {
	
	@Autowired
	ExcelDatabaseDataBean excelDbDataBean;
	@Autowired
	ExcelDatabaseService excelDbService;
	@Autowired
	ExcelDatabaseValidator validator;
	
	/**
	 * This method with open the page the
	 * @return String : upload-download.jsp
	 */
	@RequestMapping(value = "/download-table")
	public String downloadTable(ModelMap model) {
		excelDbDataBean.setDownload(true);
		excelDbDataBean.setUpload(false);
		if (excelDbDataBean.getTablesList().size() == 0) {
			excelDbDataBean.getTablesList().addAll(excelDbService.getAllTables());
		}
		model.addAttribute("excelDbDataBean", excelDbDataBean);
		return "uploadDownload";
	}
	
	/**
	 * @param bean
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/download-table/xsls-report")
	public ModelAndView downloadTableExcelFormat(@ModelAttribute("excelDbDataBean") ExcelDatabaseDataBean bean) {
		TableModel tableModel = excelDbService.getAllRecords(bean.getDownUpModel().getDownloadTableName());
		return new ModelAndView(new ExcelFileBuilder(), "tableModel", tableModel);
	}
	
	/**
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/upload-table")
	public String uploadTable(ModelMap model) {
		excelDbDataBean.setDownload(false);
		excelDbDataBean.setUpload(true);
		if (excelDbDataBean.getTablesList().size() == 0) {
			excelDbDataBean.getTablesList().addAll(excelDbService.getAllTables());
		}
		model.addAttribute("excelDbDataBean", excelDbDataBean);
		return "uploadDownload";
	}
	
	/**
	 * @param bean
	 * @param result
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/upload-table", method = RequestMethod.POST)
	public String uploadTableExcelFormat(@ModelAttribute("excelDbDataBean") ExcelDatabaseDataBean bean, 
			BindingResult result, ModelMap model) {
		
		// validating input fields
		validator.validate(bean, result);
		
		bean.setUpload(true);
		bean.getTablesList().addAll(excelDbDataBean.getTablesList());
		if (result.hasErrors()) {
			return "uploadDownload";
		}

		excelDbDataBean.getCommonBean().getErrorList().clear();
		excelDbService.uploadExcelToDatabase(bean);
		bean.setCommonBean(excelDbDataBean.getCommonBean());
//		model.addAttribute("excelDbDataBean", bean);
		return "uploadDownload";
	}
}
