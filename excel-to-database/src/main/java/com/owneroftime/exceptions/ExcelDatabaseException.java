package com.owneroftime.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


public class ExcelDatabaseException extends Exception {

	private static final long serialVersionUID = 1L;
	private List<String> errorList;
	
	public ExcelDatabaseException(String errorMessage) {
		super(errorMessage);
	}

	public List<String> getErrorList() {
		if (null == errorList) {
			errorList = new ArrayList<String>();
		}
		return errorList;
	}
	
	
	
}
