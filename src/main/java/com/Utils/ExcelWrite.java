package com.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {

	public static void writeByColumnName(String file, String sheetName, String columnName, int rowNumber,

			String value) {

		FileOutputStream fos = null;

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

			XSSFCell cell = row.createCell(colNumber);

			cell.setCellValue(value);

			fos = new FileOutputStream(file);

			workbook.write(fos);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (fos != null) {

				try {

					fos.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

	}// end writeByColumnName

}// end ExcelWrite
