package com.owneroftime.model;


public class TableInformationModel {

	private String columnName;
	private String columnDataType;
	
	public TableInformationModel() {
		// TODO Auto-generated constructor stub
	}
	
	public TableInformationModel(String columnName, String columnDataType) {
		this.columnName = columnName;
		this.columnDataType = columnDataType;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the columnDataType
	 */
	public String getColumnDataType() {
		return columnDataType;
	}

	/**
	 * @param columnDataType the columnDataType to set
	 */
	public void setColumnDataType(String columnDataType) {
		this.columnDataType = columnDataType;
	}
	
	@Override
	public String toString() {
		return "TableInformationModel [columnName=" + columnName + ", columnDataType=" + columnDataType + "]";
	}
}
