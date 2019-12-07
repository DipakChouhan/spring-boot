package com.owneroftime.bean;

import java.util.Date;

public class TODOChangesDataBean {
	private long todoId;
	private String todoName;
	private String todoAction;
	private String todoStatus;
	private Date todoActionDate = new Date();
	/**
	 * @return the todoId
	 */
	public long getTodoId() {
		return todoId;
	}
	/**
	 * @param todoId the todoId to set
	 */
	public void setTodoId(long todoId) {
		this.todoId = todoId;
	}
	/**
	 * @return the todoName
	 */
	public String getTodoName() {
		return todoName;
	}
	/**
	 * @param todoName the todoName to set
	 */
	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}
	/**
	 * @return the todoAction
	 */
	public String getTodoAction() {
		return todoAction;
	}
	/**
	 * @param todoAction the todoAction to set
	 */
	public void setTodoAction(String todoAction) {
		this.todoAction = todoAction;
	}
	/**
	 * @return the todoStatus
	 */
	public String getTodoStatus() {
		return todoStatus;
	}
	/**
	 * @param todoStatus the todoStatus to set
	 */
	public void setTodoStatus(String todoStatus) {
		this.todoStatus = todoStatus;
	}
	/**
	 * @return the todoActionDate
	 */
	public Date getTodoActionDate() {
		return todoActionDate;
	}
	/**
	 * @param todoActionDate the todoActionDate to set
	 */
	public void setTodoActionDate(Date todoActionDate) {
		this.todoActionDate = todoActionDate;
	}
	
}
