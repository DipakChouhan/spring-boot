package com.owneroftime.model;

import java.util.ArrayList;
import java.util.List;

public class TableModel {
	private List<TableInformationModel> tableInfoModelList;
	private List<String> tableRecordsList;

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
