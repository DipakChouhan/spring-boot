package com.owneroftime.repository;

import com.owneroftime.model.TableModel;

public interface ExcelDatabaseRepository {

	/**
	 * This method gets all the records and its (column_name, data_type) for a given table
	 * @param schemaName
	 * @param tableName
	 * @return TableModel
	 */
	public TableModel getAllRecords(String schemaName, String tableName);

}
