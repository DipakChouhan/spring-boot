package com.owneroftime.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.owneroftime.databean.ExcelDatabaseDataBean;
import com.owneroftime.model.TableModel;
import com.owneroftime.repository.ExcelDatabaseRepository;
import com.owneroftime.utility.ExcelDatabaseUtility;

@Service
public class ExcelDatabaseServiceImpl implements ExcelDatabaseService {
	@Autowired
	ExcelDatabaseRepository excelDbRepository;
	
	@Override
	public TableModel getAllRecords(String tableName) {
		return excelDbRepository.getAllRecords(tableName);
	}

	@Override
	public String uploadExcelToDatabase(ExcelDatabaseDataBean bean) {
		
		String fileName = bean.getDownUpModel().getUploadTableName();
		MultipartFile file = bean.getFile();
		
		ExcelDatabaseUtility util = new ExcelDatabaseUtility();
		
		// building excel and saving in server directory
		String location = util.buildExcelFromRequest(fileName, file);

		// fetch the table metadata
		TableModel tableModel = excelDbRepository.getTableColumnsMetaData( 
				bean.getDownUpModel().getUploadTableName());
		
		// validating the headers before processing the file
		util.validateTableHeaders(location, tableModel.getTableInfoModelList());
		
		// reading excel and preparing table model
		try {
			util.processExcel(location, tableModel);
			excelDbRepository.saveAllRecords(tableModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<String> getAllTables() {
		return excelDbRepository.getAllTables();
	}

}
