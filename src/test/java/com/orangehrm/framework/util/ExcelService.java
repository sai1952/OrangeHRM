package com.orangehrm.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelService {

	public Iterator<Object[]> readTestDataFromExcel(String sWorkBookName, String sWorkbookSheetName, String tableName) {
		String testDataFolderPath = System.getProperty("user.dir")+"\\TestData";
		List<Hashtable<String, String>> testData = null;
		for (File testDataFile : new File(testDataFolderPath).listFiles()) {
			if (!testDataFile.getAbsolutePath().contains("$")) {
				FileInputStream file = null;
				if (testDataFile.getPath().contains(sWorkBookName)) {
					try {
						file = new FileInputStream(testDataFile);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					Workbook workBook = null;
					try {
						workBook = WorkbookFactory.create(file);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InvalidFormatException e) {
						e.printStackTrace();
					}
					Sheet testDataSheet = workBook.getSheet(sWorkbookSheetName);
					testData = getTestDataBySheet(testDataSheet, tableName);
				}
			}
		}
		System.out.println("test data loaded successfully, TEST DATA :"+testData);
		return getIterator(testData);
	}
	
    public static Workbook create(POIFSFileSystem fs) throws IOException {
        return new HSSFWorkbook(fs);
    }
	
	private Iterator<Object[]> getIterator(List<Hashtable<String, String>> testDataList) {
		List<Object[]> iteratorList = new ArrayList<Object[]>();
		for (Map map : testDataList) {
			iteratorList.add(new Object[] { map });
		}
		return iteratorList.iterator();
	}
	
	private int getStartRow(Sheet testDataSheet, String tableName) {
		for (int i = 0; i < testDataSheet.getLastRowNum() + 1; i++) {
			try {
				if (testDataSheet.getRow(i).getCell(0).getStringCellValue().toString().equalsIgnoreCase(tableName))
					return i;
			} catch (Exception e) {
			}
		}
		return 0;
	}
	
	private int getEndRow(Sheet testDataSheet, String tableName, int startRow) {
		for (int i = startRow + 1; i < testDataSheet.getLastRowNum() + 1; i++) {
			try {
				if (testDataSheet.getRow(i).getCell(0).getStringCellValue().toString().equalsIgnoreCase(tableName))
					return i;
			} catch (Exception e) {

			}
		}
		return startRow;
	}
	
	private List<String> getHeaders(Sheet sheet, int row) {
		List<String> headers = new ArrayList<String>();
		Row headerRow = sheet.getRow(row);
		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			Cell dataCell = headerRow.getCell(i, Row.RETURN_NULL_AND_BLANK);
			if (dataCell == null)
				headers.add("CELL NOT FOUND");
			else if ("".equals(dataCell.getStringCellValue().trim()))
				headers.add("MISSING CONTENT");
			else {
				dataCell.setCellType(Cell.CELL_TYPE_STRING);
				headers.add(dataCell.getStringCellValue());
			}
		}
		return headers;
	}
	
	private List<Hashtable<String, String>> getTestDataBySheet(Sheet testDataSheet, String tableName) {
		List<Hashtable<String, String>> testData = new ArrayList<Hashtable<String, String>>();
		int startRow, endRow;
		// System.out.println("testDataSheet"+testDataSheet);
		startRow = getStartRow(testDataSheet, tableName);
		List<String> headers = getHeaders(testDataSheet, startRow + 1);
		endRow = getEndRow(testDataSheet, tableName, startRow);
		for (int i = startRow + 2; i < endRow; i++) {
			Hashtable<String, String> map = new Hashtable<String, String>();
			Row dataRow = testDataSheet.getRow(i);
			Cell dataCell;
			try {
				if (headers.contains("RunIteration") && !testDataSheet.getRow(i).getCell(1).getStringCellValue()
						.toString().equalsIgnoreCase("Yes")) {
					System.out.println("The current iteration :" + i + " is ignore as it set to not run");
					continue;
				}
			} catch (Exception e) {
				System.out.println("The current iteration :" + i + " is ignore as it set to blank ");
				continue;
			}
			for (int j = 1; j < headers.size(); j++) {
				if (!headers.get(j).equals("CELL NOT FOUND") && !headers.get(j).equals("MISSING CONTENT")) {
					try {
						dataCell = dataRow.getCell(j, Row.RETURN_NULL_AND_BLANK);
						if (dataCell != null) {
							dataCell.setCellType(Cell.CELL_TYPE_STRING);
						}
						if (dataCell != null && !("".equals(dataCell.getStringCellValue().trim()))) {
							map.put(headers.get(j), dataCell.getStringCellValue());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			testData.add(map);

		}
		return testData;
	}
	
	
	
}
