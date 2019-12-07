package com.owneroftime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.owneroftime.model.TODOModel;

public interface TODORepository extends JpaRepository<TODOModel, Long>{
	
	@Query("FROM TODOModel WHERE todoId = ?1")
	public TODOModel findTODOById(long todoId);
	
}
