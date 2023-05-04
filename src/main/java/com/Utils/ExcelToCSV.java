package com.Utils;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToCSV {

	public void convertExcelToCSV(String PathFileInput, String PathFileOuput) {

		// Creating a inputFile object with specific file path

		File inputFile = new File(PathFileInput);

		// Creating a outputFile object to write excel data to csv

		File outputFile = new File(PathFileOuput);

		// For storing data into CSV files

		StringBuffer data = new StringBuffer();

		try {

			// Creating input stream

			FileInputStream fis = new FileInputStream(inputFile);

			Workbook workbook = null;

			// Get the workbook object for Excel file based on file format

			if (inputFile.getName().endsWith(".xlsx")) {

				workbook = new XSSFWorkbook(fis);

			} else if (inputFile.getName().endsWith(".xls")) {

				workbook = new HSSFWorkbook(fis);

			} else {

				fis.close();

				throw new Exception("File not supported!");

			}

			// Get first sheet from the workbook

			Sheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();

				// For each row, iterate through each columns

				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {

					case BOOLEAN:

						data.append(cell.getBooleanCellValue() + ",");

						break;

					case NUMERIC:

						data.append(cell.getNumericCellValue() + ",");

						break;

					case STRING:

						data.append(cell.getStringCellValue() + ",");

						break;

					case BLANK:

						data.append("" + ",");

						break;

					default:

						data.append(cell + ",");

					}

				}

				// appending new line after each row

				data.append('\n');

			}

			FileOutputStream fos = new FileOutputStream(outputFile);

			fos.write(data.toString().getBytes());

			fos.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("Conversion of an Excel file to CSV file is done!");

	}

}// end ExcelToCSV
