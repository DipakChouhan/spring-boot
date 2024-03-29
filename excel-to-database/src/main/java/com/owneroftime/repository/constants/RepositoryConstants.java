package com.owneroftime.repository.constants;


public class RepositoryConstants {
	
	private RepositoryConstants() {}
	
	public static final String QUERY_GET_TABLE_INFORMATION = "select column_name, data_type " + 
											"from information_schema.columns where table_name=:table_name";
	
	public static final String QUERY_MYSQL_GET_ALL_TABLE = "select table_name from information_schema.tables";
	
	public static final String QUERY_GET_ALL_FROM_TABLE = "select * from ";
	
	public static final String EMPTY_STRING = "";
	
	public static final String LEFT_BIG_BRACKET = "[";
	
	public static final String RIGHT_BIG_BRACKET = "]";
}
