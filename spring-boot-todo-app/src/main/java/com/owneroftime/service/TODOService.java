package com.owneroftime.service;

import java.util.List;

import com.owneroftime.model.TODOModel;

public interface TODOService {
	
	/**
	 * Method to get all the TODOs available in the database 
	 */
	public List<TODOModel> getTodoList();
	
	/**
	 * Method to update a TODO in the database
	 */
	public TODOModel updateTODO(TODOModel todoModel);

	public TODOModel findTODOById(long todoId);

	public void deleteTODOById(long todoId);

	public TODOModel addTODO(TODOModel todo);
}
