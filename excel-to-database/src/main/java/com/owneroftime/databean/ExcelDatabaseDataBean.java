package com.owneroftime.databean;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.owneroftime.model.DownloadUploadModel;

@Component
public class ExcelDatabaseDataBean {
	private List<String> tablesList = new ArrayList<String>();
	private DownloadUploadModel downUpModel;
	private boolean upload = false;
	private boolean download = false;
	private MultipartFile file = null;
	private List<String> errorList;
	@Autowired
	private CommonDataBean commonBean;

	/**
	 * @return the commonBean
	 */
	public CommonDataBean getCommonBean() {
		return commonBean;
	}

	/**
	 * @param commonBean the commonBean to set
	 */
	public void setCommonBean(CommonDataBean commonBean) {
		this.commonBean = commonBean;
	}

	public List<String> getErrorList() {
		if (null == errorList) {
			errorList = new ArrayList<String>();
		}
		return errorList;
	}

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
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
