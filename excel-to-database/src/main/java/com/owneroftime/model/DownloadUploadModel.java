package com.owneroftime.model;


public class DownloadUploadModel {
	private String uploadTableName;
	private String downloadTableName;
	private String uploadTableSchemaName;
	private String downloadTableSchemaName;

	/**
	 * @return the uploadTableSchemaName
	 */
	public String getUploadTableSchemaName() {
		return uploadTableSchemaName;
	}

	/**
	 * @param uploadTableSchemaName the uploadTableSchemaName to set
	 */
	public void setUploadTableSchemaName(String uploadTableSchemaName) {
		this.uploadTableSchemaName = uploadTableSchemaName;
	}
	
	/**
	 * @return the downloadTableSchemaName
	 */
	public String getDownloadTableSchemaName() {
		return downloadTableSchemaName;
	}

	/**
	 * @param downloadTableSchemaName the downloadTableSchemaName to set
	 */
	public void setDownloadTableSchemaName(String downloadTableSchemaName) {
		this.downloadTableSchemaName = downloadTableSchemaName;
	}

	/**
	 * @return the uploadTableName
	 */
	public String getUploadTableName() {
		return uploadTableName;
	}

	/**
	 * @param uploadTableName the uploadTableName to set
	 */
	public void setUploadTableName(String uploadTableName) {
		this.uploadTableName = uploadTableName;
	}

	/**
	 * @return the downloadTableName
	 */
	public String getDownloadTableName() {
		return downloadTableName;
	}

	/**
	 * @param downloadTableName the downloadTableName to set
	 */
	public void setDownloadTableName(String downloadTableName) {
		this.downloadTableName = downloadTableName;
	}

}
