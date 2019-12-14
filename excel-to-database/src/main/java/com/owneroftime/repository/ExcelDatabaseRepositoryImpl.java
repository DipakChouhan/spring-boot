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

import com.owneroftime.databean.CommonDataBean;
import com.owneroftime.enums.DateEnum;
import com.owneroftime.enums.NumberEnum;
import com.owneroftime.enums.StringEnum;
import com.owneroftime.model.TableInformationModel;
import com.owneroftime.model.TableModel;
import com.owneroftime.repository.constants.RepositoryConstants;

@Repository
public class ExcelDatabaseRepositoryImpl implements ExcelDatabaseRepository {

	@Autowired
	EntityManager entityManager;
	@Autowired
	CommonDataBean commonBean;

	private Session getSession() {
		Session session = (Session) entityManager.unwrap(Session.class);
		return session;
	}

	@Override
	@Transactional
	public TableModel getAllRecords(String tableName) {
		// getting columns metadata
		TableModel tableModel = getTableColumnsMetaData(tableName);

		// populate all records from database
		populateAllRecords(tableName, tableModel);
		return tableModel;
	}

	/**
	 * This method populate the table information (COLUMN_NAME, DATA_TYPE)
	 * 
	 * @param resultList
	 * @param tableInfoModelList
	 * @param tableModel
	 */
	private void populateTableInformation(List<Object[]> resultList, TableModel tableModel) {
		List<TableInformationModel> tableInfoModelList = new ArrayList<TableInformationModel>();
		for (Object[] obj : resultList) {
			String column = (String) obj[0];
			String type = (String) obj[1];

			TableInformationModel tableInfoModel = new TableInformationModel();

			tableInfoModel.setColumnName(column);
			tableInfoModel.setColumnDataType(type);

			tableInfoModelList.add(tableInfoModel);

			// populating column map with java data types
			if (DateEnum.TIMESTAMP.contains(type.toUpperCase())) {
				tableModel.getColumnMap().put(column, DateEnum.DATE.toString());
			} else if (NumberEnum.NUMBER.contains(type.toUpperCase())) {
				tableModel.getColumnMap().put(column, NumberEnum.NUMBER.toString());
			} else if (StringEnum.CHAR.contains(type.toUpperCase())) {
				tableModel.getColumnMap().put(column, StringEnum.STRING.toString());
			}
		}

		// adding table information list to table model
		tableModel.getTableInfoModelList().addAll(tableInfoModelList);
	}

	/**
	 * This method populate all the records string in form in List
	 * 
	 * @param tableName
	 * @param tableModel
	 */
	private void populateAllRecords(String tableName, TableModel tableModel) {
		Session session = this.getSession();

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = session.createNativeQuery(RepositoryConstants.QUERY_GET_ALL_FROM_TABLE + tableName)
				.getResultList();

		// convert records to list
		convertResultsetToList(resultList, tableModel);
	}

	/**
	 * This method converts the result set to list of strings
	 * 
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
			i = i + 1;
		}

		// adding records list to table model
		tableModel.getTableRecordsList().addAll(tableRecordsList);
	}

	@Override
	@Transactional
	public TableModel getTableColumnsMetaData(String tableName) {
		TableModel tableModel = new TableModel();

		// getting the session
		Session session = this.getSession();

		@SuppressWarnings("rawtypes")
		Query query = session.createNativeQuery(RepositoryConstants.QUERY_GET_TABLE_INFORMATION);

		query.setParameter("table_name", tableName);

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();

		// populate table information (table_name, data_type)
		populateTableInformation(resultList, tableModel);

		// setting table name to table model
		tableModel.setTableName(tableName);
		return tableModel;
	}

	@Override
	@Transactional
	public List<String> getAllTables() {
		// getting the session
		Session session = this.getSession();

		@SuppressWarnings("unchecked")
		List<String> tableList = session.createNativeQuery(RepositoryConstants.QUERY_MYSQL_GET_ALL_TABLE).list();

		return tableList;
	}

	/**
	 * @param tableModel
	 */
	@Override
	public void saveAllRecords(TableModel tableModel) {
		Session session = this.getSession();
		String tempTAbleName = "TEMP_" + tableModel.getTableName();

		try {
			session.beginTransaction();
			if (createTemporaryTable(tableModel, tempTAbleName, session)) {
				boolean copied = backupDataToTempTable(tableModel, tempTAbleName, session);
				if (copied) {
					System.out.println("Sucessfully backed up");
					int rowsDeleted = session.createNativeQuery("delete from " + tableModel.getTableName())
							.executeUpdate();
					System.out.println("TRUNCATED : " + rowsDeleted);
					if (rowsDeleted >= 0) {
						for (int i = 0; i < tableModel.getInsertStrings().size(); i++) {
							int result = session.createNativeQuery(tableModel.getInsertStrings().get(i))
									.executeUpdate();
							System.out.println("RESULT : " + result);
							if (i % 50 == 0) {
								// flush a batch of inserts and release memory:
								session.flush();
								session.clear();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			if (commonBean.getErrorList().isEmpty()) {
				commonBean.getErrorList().add("Error occurred while inserting data into: " 
											+ tableModel.getTableName().toUpperCase());
			}
		} finally {
			if (null != session) {
				session.getTransaction().commit();
				session.close();
			}
		}

	}

	/**
	 * Method to build query to insert data into temp table from original table
	 * 
	 * @param tableModel
	 * @param session2
	 * @return String
	 */
	private boolean backupDataToTempTable(TableModel tableModel, String tempTAbleName, Session session) {
		// first construct the insert select query
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ").append(tempTAbleName).append(" ( ").append(tableModel.getColumnString())
				.append(" ) ").append(" SELECT ").append(tableModel.getColumnString()).append(" FROM ")
				.append(tableModel.getTableName());
		
		System.out.println(query);
		
		// execute the query to backup table data
		try {
			int result = session.createNativeQuery(query.toString()).executeUpdate();
			if (result >= 0) {
				System.out.println("TEMP TABLE : " + result);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonBean.getErrorList().add("Error ocurred while backing up data from " 
									+ tableModel.getTableName().toUpperCase()
									+ " to "
									+ tempTAbleName.toUpperCase());
		}
		return false;
	}

	/**
	 * @param tableModel
	 * @param tempTAbleName
	 * @param session
	 * @return boolean
	 */
	private boolean createTemporaryTable(TableModel tableModel, String tempTAbleName, Session session) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("CREATE TABLE ").append(tempTAbleName).append(" (");
		int index = 0;
		for (TableInformationModel model : tableModel.getTableInfoModelList()) {
			String columnType = model.getColumnDataType().toUpperCase();
			String ColumnName = model.getColumnName();
			if (index == 0) {
				queryBuilder.append(ColumnName).append(" ").append(columnType);
				if (StringEnum.STRING.contains(columnType) || NumberEnum.NUMBER.contains(columnType)) {
					queryBuilder.append("(255)");
				}

				queryBuilder.append(" PRIMARY KEY");
			} else {
				queryBuilder.append(",").append(ColumnName).append(" ").append(columnType);
				if (StringEnum.STRING.contains(columnType) || NumberEnum.NUMBER.contains(columnType)) {
					queryBuilder.append("(255)");
				}
			}
			index++;
		}
		;
		queryBuilder.append(")");

		System.out.println(queryBuilder);

		// check if table exists and create if does not exists
		boolean tempTableExists = checkIfTableExist(tempTAbleName, session);
		if (!tempTableExists && commonBean.getErrorList().size() == 0) {
			try {
				session.createNativeQuery(queryBuilder.toString()).executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				commonBean.getErrorList().add("Error ocurred while creating temp table: " 
											+ tempTAbleName.toUpperCase());
			}
		} else if (tempTableExists && commonBean.getErrorList().size() == 0) {
			try {
				int result = session.createNativeQuery("delete from " + tempTAbleName).executeUpdate();
				System.out.println("TRUNCATE TEMP TABLE : " + result);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				commonBean.getErrorList().add("Error occurred while deleting data from temp table: " 
											+ tempTAbleName.toUpperCase());
			}
		}
		return false;
	}

	/**
	 * @param tempTableName
	 * @return boolean
	 */
	private boolean checkIfTableExist(String tempTableName, Session session) {
		try {
			@SuppressWarnings("rawtypes")
			Query query = session
					.createNativeQuery("select count(*) from information_schema.tables where table_name = :table_name");
			query.setParameter("table_name", tempTableName.toUpperCase());
			@SuppressWarnings("unchecked")
			List<Object[]> result = query.getResultList();
			if (result.size() > 0) {
				System.out.println("result size : " + result.size());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonBean.getErrorList().add("Error occurred while checking if temp table exists: " 
										+ tempTableName.toUpperCase());
		}
		return false;
	}
}
