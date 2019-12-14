package com.owneroftime.databean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommonDataBean {
	private List<String> errorList;

	public List<String> getErrorList() {
		if (null == errorList) {
			errorList = new ArrayList<String>();
		}
		return errorList;
	}
	
}
