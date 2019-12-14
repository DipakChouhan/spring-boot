package com.owneroftime.repository;

import java.util.List;

import com.owneroftime.model.TableModel;

public interface ExcelDatabaseRepository {

	/**
	 * This method gets all the records and its (column_name, data_type) 
	 * for a given table
	 * @param tableName
	 * @return TableModel
	 */
	public TableModel getAllRecords(String tableName);
	
	/**
	 * This method gets the metadata related columns of the given table 
	 * (column_name, data_type)
	 * @param tableName
	 * @return
	 * TableModel
	 */
	public TableModel getTableColumnsMetaData(String tableName);
	
	public List<String> getAllTables();

	/**
	 * @param tableModel
	 * void
	 */
	public void saveAllRecords(TableModel tableModel);

}
