package com.owneroftime.model;


public class DownloadUploadModel {
	private String uploadTableName;
	private String downloadTableName;

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
