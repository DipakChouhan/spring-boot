package com.owneroftime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.owneroftime.model.TODOModel;
import com.owneroftime.repository.TODORepository;

@Service
@Transactional
public class TODOServiceImpl implements TODOService {
	
	@Autowired
	private TODORepository todoRepository;
	
	@Override
	public List<TODOModel> getTodoList() {
		return todoRepository.findAll();
	}

	@Override
	public TODOModel updateTODO(TODOModel todoModel) {
		return todoRepository.save(todoModel);
	}

	@Override
	public TODOModel findTODOById(long todoId) {
		return todoRepository.findTODOById(todoId);
	}

	@Override
	public void deleteTODOById(long todoId) {
		todoRepository.deleteById(todoId);
	}

	@Override
	public TODOModel addTODO(TODOModel todo) {
		return todoRepository.save(todo);
	}
}
