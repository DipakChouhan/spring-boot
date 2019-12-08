package com.owneroftime.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.owneroftime.model.TableInformationModel;
import com.owneroftime.model.TableModel;
import com.owneroftime.repository.constants.RepositoryConstants;


@Repository
@Transactional
public class ExcelDatabaseRepositoryImpl implements ExcelDatabaseRepository{
	
	@Autowired
	EntityManager entityManager;

	private Session getSession() {
		Session session = (Session) entityManager.unwrap(Session.class);
		return session;
	}
	
	@Override
	public TableModel getAllRecords(String schemaName, String tableName) {
		TableModel tableModel = new TableModel();
		
		// getting the session
		Session session = this.getSession();
		
		@SuppressWarnings("rawtypes")
		Query query = session.createNativeQuery(RepositoryConstants.QUERY_GET_TABLE_INFORMATION);
		
		query.setParameter("schema_name", schemaName);
		query.setParameter("table_name", tableName);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		
		// populate table information (table_name, data_type)
		populateTableInformation(resultList, tableModel);
		
		// populate all records from database
		populateAllRecords(tableName, tableModel, session);
		
		return tableModel;
	}
	
	/**
	 * This method populate the table information (COLUMN_NAME, DATA_TYPE)
	 * @param resultList
	 * @param tableInfoModelList
	 * @param tableModel 
	 */
	private void populateTableInformation(List<Object[]> resultList, TableModel tableModel) {
		List<TableInformationModel> tableInfoModelList = new ArrayList<TableInformationModel>();
		for (Object[] obj : resultList) {
			TableInformationModel tableInfoModel = new TableInformationModel();
			tableInfoModel.setColumnName((String) obj[0]);
			tableInfoModel.setColumnDataType((String) obj[1]);
			tableInfoModelList.add(tableInfoModel);
		}
		
		// adding table information list to table model
		tableModel.getTableInfoModelList().addAll(tableInfoModelList);
	}
	
	/**
	 * This method populate all the records string in form in List
	 * @param tableName
	 * @param tableModel
	 * @param session
	 */
	private void populateAllRecords(String tableName, TableModel tableModel, 
			Session session) {
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = session
				.createNativeQuery(RepositoryConstants.QUERY_GET_ALL_FROM_TABLE + tableName)
				.getResultList();
		
		// convert records to list
		convertResultsetToList(resultList, tableModel);
	}
	
	/**
	 * This method converts the result set to list of strings
	 * @param resultList
	 * @param tableRecordsList
	 * @param tableModel 
	 */
	private void convertResultsetToList(List<Object[]> resultList, TableModel tableModel) {
		List<String> tableRecordsList = new ArrayList<String>();
		int i = 0;
		for (Object[] s : resultList) {
			tableRecordsList.add(Arrays.asList(s).toString()
					.replace(RepositoryConstants.LEFT_BIG_BRACKET, RepositoryConstants.EMPTY_STRING)
					.replace(RepositoryConstants.RIGHT_BIG_BRACKET, RepositoryConstants.EMPTY_STRING));
			i = i +1;
		}
		
		// adding records list to table model
		tableModel.getTableRecordsList().addAll(tableRecordsList);
//		System.out.println(tableRecordsList.get(0));
	}
}
