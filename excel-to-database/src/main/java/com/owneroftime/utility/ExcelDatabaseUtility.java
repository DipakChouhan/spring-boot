package com.owneroftime.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.owneroftime.excelbuilder.ExcelFileReader;
import com.owneroftime.model.TableInformationModel;
import com.owneroftime.model.TableModel;

public class ExcelDatabaseUtility {
	
	public String buildExcelFromRequest(String fileName, MultipartFile file) {
		File serverFile = null;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				
				if (!dir.exists()) {
					dir.mkdirs();
				}
				
				// Create the file on server
				serverFile = new File(dir.getAbsolutePath()	+ File.separator + fileName + ".xlsx");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				return serverFile.getAbsolutePath();

			} catch (Exception e) {
				// create exception message and handle it
			}
		} else {
			return null;
		}
		return null;
	}


	public void processExcel(String location, TableModel tableModel) throws IOException, ParseException {
		ExcelFileReader reader = new ExcelFileReader();
		reader.read(location, tableModel);
	}


	/**
	 * @param location
	 * @param tableInfoModelList
	 */
	public void validateTableHeaders(String location, List<TableInformationModel> tableInfoModelList) {
		
	}
}
