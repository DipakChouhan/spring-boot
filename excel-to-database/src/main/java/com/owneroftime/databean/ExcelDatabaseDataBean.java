package com.owneroftime.databean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.owneroftime.model.DownloadUploadModel;

@Component
public class ExcelDatabaseDataBean {
	private List<String> tablesList = new ArrayList<String>();
	private DownloadUploadModel downUpModel;
	private boolean upload = false;
	private boolean download = false;

	/**
	 * @return the downUpModel
	 */
	public DownloadUploadModel getDownUpModel() {
		if (null == downUpModel) {
			downUpModel = new DownloadUploadModel();
		}
		return downUpModel;
	}


	/**
	 * @return the upload
	 */
	public boolean isUpload() {
		return upload;
	}

	/**
	 * @param upload the upload to set
	 */
	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	/**
	 * @return the download
	 */
	public boolean isDownload() {
		return download;
	}

	/**
	 * @param download the download to set
	 */
	public void setDownload(boolean download) {
		this.download = download;
	}

	/**
	 * @return the tablesList
	 */
	public List<String> getTablesList() {
//		if(null == this.tablesList) {
//			this.tablesList = new ArrayList<String>();
//		}
		return tablesList;
	}
}
