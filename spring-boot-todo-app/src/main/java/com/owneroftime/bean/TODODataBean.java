package com.owneroftime.bean;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.owneroftime.model.TODOModel;

@Component
@Scope(scopeName = "singleton")
public class TODODataBean {
	private List<TODOModel> todoList;
	private TODOModel todo = new TODOModel();
//	private TODOChangesDataBean todoChanges;
	/**
	 * @return the todoList
	 */
	public List<TODOModel> getTodoList() {
		return todoList;
	}

	/**
	 * @param todoList the todoList to set
	 */
	public void setTodoList(List<TODOModel> todoList) {
		this.todoList = todoList;
	}

	/**
	 * @return the todo
	 */
	public TODOModel getTodo() {
		return todo;
	}

	/**
	 * @param todo the todo to set
	 */
	public void setTodo(TODOModel todo) {
		this.todo = todo;
	}
}
