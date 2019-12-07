package com.owneroftime.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TODO")
public class TODOModel {
	@Id
	@Column(name = "TODO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long todoId;
	@Column(name = "TODO_NAME")
	private String todoName;
	@Column(name = "TODO_CONTENT")
	private String todoContent;
	@Column(name = "TODO_CREATED_DATE")
	private Date todoCreatedDate = new Date();
	@Column(name = "TODO_STATUS")
	private String todoStatus;
	@Column(name = "TODO_START_DATE")
	private Date todoStartDate = new Date();
	@Column(name = "TODO_END_DATE")
	private Date todoEndDate = new Date();

	/**
	 * @return the todoId
	 */
	public Long getTodoId() {
		return todoId;
	}

	/**
	 * @param todoId the todoId to set
	 */
	public void setTodoId(Long todoId) {
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
	 * @return the todoContent
	 */
	public String getTodoContent() {
		return todoContent;
	}

	/**
	 * @param todoContent the todoContent to set
	 */
	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}

	/**
	 * @return the todoCreatedDate
	 */
	public String getTodoCreatedDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(this.todoCreatedDate);
	}

	/**
	 * @param todoCreatedDate the todoCreatedDate to set
	 */
	public void setTodoCreatedDate(Date todoCreatedDate) {
		this.todoCreatedDate = todoCreatedDate;
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
	 * @return the todoStartDate
	 */
	public String getTodoStartDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(this.todoStartDate);
	}

	/**
	 * @param todoStartDate the todoStartDate to set
	 */
	public void setTodoStartDate(Date todoStartDate) {
		this.todoStartDate = todoStartDate;
	}

	/**
	 * @return the todoEndDate
	 */
	public String getTodoEndDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(this.todoEndDate);
	}

	/**
	 * @param todoEndDate the todoEndDate to set
	 */
	public void setTodoEndDate(Date todoEndDate) {
		this.todoEndDate = todoEndDate;
	}

	@Override
	public String toString() {
		return "TODOModel [todoId=" + todoId + ", todoName=" + todoName + ", todoContent=" + todoContent
				+ ", todoCreatedDate=" + todoCreatedDate + ", todoStatus=" + todoStatus + ", todoStartDate="
				+ todoStartDate + ", todoEndDate=" + todoEndDate + "]";
	}
	
	

}
