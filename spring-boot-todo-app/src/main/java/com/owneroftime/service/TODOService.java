package com.owneroftime.service;

import java.util.List;

import com.owneroftime.model.TODOModel;

public interface TODOService {
	
	/**
	 * Method to get all the TODOs available in the database
	 * @return List<TODOModel> : List of TODOs
	 */
	public List<TODOModel> getTodoList();
	
	/**
	 * Method to update a TODO in the database
	 * @param todoModel : TODO to be updated
	 * @return TODOModel : Updated TODO
	 */
	public TODOModel updateTODO(TODOModel todoModel);

	/**
	 * Method to find the TODO by passing its ID
	 * @param todoId : Id of the TODO to be fetched
	 * @return TODOModel : Fetched TODO
	 */
	public TODOModel findTODOById(long todoId);

	/**
	 * Method to delete a TODO by Id
	 * @param todoId : Id of the TODO to be deleted
	 */
	public void deleteTODOById(long todoId);

	/**
	 * Method to create a new TODO
	 * @param todo : TODO to be created
	 * @return TODOModel : Created TODO
	 */
	public TODOModel addTODO(TODOModel todo);
}
