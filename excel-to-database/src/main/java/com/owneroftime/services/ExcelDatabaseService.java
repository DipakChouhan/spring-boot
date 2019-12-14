package com.owneroftime.services;

import java.util.List;

import com.owneroftime.databean.ExcelDatabaseDataBean;
import com.owneroftime.model.TableModel;

public interface ExcelDatabaseService {
	
	public TableModel getAllRecords(String tableName);
	public String uploadExcelToDatabase(ExcelDatabaseDataBean bean);
	public List<String> getAllTables();
}
