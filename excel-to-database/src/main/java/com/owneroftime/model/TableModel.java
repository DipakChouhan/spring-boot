package com.owneroftime.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableModel {
	private String tableName;
	private List<TableInformationModel> tableInfoModelList;
	private List<String> tableRecordsList;
	private Map<String, String> columnMap;
	private Map<Integer, Object> recordMap;
	private String columnString;
	private List<String> insertStrings;

	/**
	 * @return the insertStrings
	 */
	public List<String> getInsertStrings() {
		if (null == insertStrings) {
			insertStrings = new ArrayList<String>();
		}
		return insertStrings;
	}

	/**
	 * @return the columnString
	 */
	public String getColumnString() {
		return columnString;
	}

	/**
	 * @param columnString the columnString to set
	 */
	public void setColumnString(String columnString) {
		this.columnString = columnString;
	}

	/**
	 * @return the recordMap
	 */
	public Map<Integer, Object> getRecordMap() {
		if (null == recordMap) {
			recordMap = new HashMap<Integer, Object>();
		}
		return recordMap;
	}

	/**
	 * @return the columnMap
	 */
	public Map<String, String> getColumnMap() {
		if (null == columnMap) {
			columnMap = new HashMap<String, String>();
		}
		return columnMap;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tableInfoModel
	 */
	public List<TableInformationModel> getTableInfoModelList() {
		if (null == tableInfoModelList) {
			tableInfoModelList = new ArrayList<TableInformationModel>();
		}
		return tableInfoModelList;
	}

	/**
	 * @return the tableRecords
	 */
	public List<String> getTableRecordsList() {
		if (null == tableRecordsList) {
			tableRecordsList = new ArrayList<String>();
		}
		return tableRecordsList;
	}


}
