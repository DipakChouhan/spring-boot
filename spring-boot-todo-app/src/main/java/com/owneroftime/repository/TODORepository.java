package com.owneroftime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.owneroftime.model.TODOModel;

public interface TODORepository extends JpaRepository<TODOModel, Long>{
	
	/**
	 * Custom Method to fetch TODO based on Id. Passing a custom query.
	 * @param todoId : Id of the TODO that needs to be fetched
	 * @return {@link TODOModel}
	 */
	@Query("FROM TODOModel WHERE todoId = ?1")
	public TODOModel findTODOById(long todoId);
	
}
