package com.Utils;

import java.io.IOException;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	XSSFRow row;

	public static String readByColumnName(final String file, final String sheetName, final String columnName, final int rowNumber) {

		String cellValue = null;

		try (FileInputStream fs = new FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fs);) {

			XSSFSheet sheet = workbook.getSheet(sheetName);

			XSSFRow row = sheet.getRow(0);

			int lastColumn = row.getLastCellNum();

			int colNumber = 0;

			for (int i = 0; i < lastColumn; i++) {

				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {

					colNumber = i;

					break;

				}

			}

			row = sheet.getRow(rowNumber);

			XSSFCell cell = row.getCell(colNumber);

			if (cell != null) {

				cellValue = cell.getStringCellValue();

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		return cellValue;

	}// end readByColumnName

}// end ExcelRead