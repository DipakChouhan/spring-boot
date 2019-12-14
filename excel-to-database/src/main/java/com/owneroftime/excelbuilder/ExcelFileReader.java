package com.owneroftime.excelbuilder;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.owneroftime.excelbuilder.constants.ExcelBuilderConstants;
import com.owneroftime.model.TableModel;

public class ExcelFileReader {

	public void read(String location, TableModel tableModel) throws IOException, ParseException {
		Workbook workbook = null;
		try {
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			workbook = WorkbookFactory.create(new File(location));

			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			StringBuilder excelHeaderString = new StringBuilder();

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			// check if header columns and table columns are same
			boolean isHeaderValid = booleanvalidateColumnOrderAndName(tableModel, sheet, dataFormatter,
					excelHeaderString);

			if (isHeaderValid) {
				// building list of columns
				List<String> columns = Arrays.asList(excelHeaderString.toString().split(","));
				tableModel.setColumnString(excelHeaderString.toString());
				
				// looping through all records in excel
				for (Row row : sheet) {
					StringBuilder queryBuilder = new StringBuilder();
					
					// skipping header record
					if (row.getRowNum() != 0) {
						
						// building initial insert string
						queryBuilder.append("INSERT INTO ").append(tableModel.getTableName()).append(" (")
								.append(tableModel.getColumnString()).append(") VALUES (");
						
						// looping through all columns of excel
						for (Cell cell : row) {
							int i = cell.getColumnIndex();
							String type = tableModel.getColumnMap().get(columns.get(i));
							String cellValue = dataFormatter.formatCellValue(cell).trim();
							
							// formatting data based on its type
							if (type.equals("STRING")) {
								if (cellValue.equals("null"))
									tableModel.getRecordMap().put(i,cellValue);
								else
									tableModel.getRecordMap().put(i, "'" + cellValue + "'");
							} else if (type.equals("NUMBER")) {
								tableModel.getRecordMap().put(i, Integer.parseInt(cellValue));
							} else if (type.equals("DATE")) {
								DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
						        
						        LocalDateTime datetime = LocalDateTime.parse(cellValue, oldPattern);
						        String output = datetime.format(newPattern);
								tableModel.getRecordMap().put(i, ExcelBuilderConstants.DB_DATE_FUNCTION + output
										+ ExcelBuilderConstants.DB_DATE_FORMAT);
							}
						}
						
						// Adding formatted columns to builder
						tableModel.getRecordMap().forEach((key1, value) -> {
							if (key1 == 0)
								queryBuilder.append(value);
							else
								queryBuilder.append(",").append(value);
						});

						queryBuilder.append(")");
						
						// setting build insert query to table model list
						tableModel.getInsertStrings().add(queryBuilder.toString());

						System.out.println(queryBuilder.toString());

					}
				};
			}
		} finally {
			if (null != workbook) {
				workbook.close();
			}
		}
	}

	/**
	 * This method build excel and table header string and validates if they are
	 * same or not
	 * 
	 * @param tableModel
	 * @param header
	 * @param dataFormatter
	 * @param excelHeaderString
	 * @return boolean
	 */
	private boolean booleanvalidateColumnOrderAndName(TableModel tableModel, Sheet sheet, DataFormatter dataFormatter,
			StringBuilder excelHeaderString) {
		StringBuilder tableHeaderString = new StringBuilder();
		Row header = sheet.getRow(0);
		// building excel header string
		header.forEach(cell -> {
			if (excelHeaderString.toString().isEmpty()) {
				excelHeaderString.append(dataFormatter.formatCellValue(cell));
			} else {
				excelHeaderString.append(",").append(dataFormatter.formatCellValue(cell));
			}
		});

		// building table header string
		tableModel.getTableInfoModelList().forEach(tabInfoModel -> {
			if (tableHeaderString.toString().isEmpty()) {
				tableHeaderString.append(tabInfoModel.getColumnName());
			} else {
				tableHeaderString.append(",").append(tabInfoModel.getColumnName());
			}
		});

		// both header string are equal implies header info matched
		if (null != excelHeaderString && null != tableHeaderString
				&& excelHeaderString.toString().equals(tableHeaderString.toString())) {
			return true;
		}
		return false;
	}

}
