package com.Practice1JUnit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.Utils.ExcelRead;
import com.Utils.ExcelWrite;
import com.Utils.ExcelToCSV;
import java.io.File;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Example2")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUExample2Test {
	
	ExcelRead excelRead = new ExcelRead();
	ExcelWrite excelWrite = new ExcelWrite();
	ExcelToCSV excelToCSV = new ExcelToCSV();
	String pathExcel = "Files\\Template.xlsx";
	
	
	@Test
	@Order(1)
	@DisplayName("A - Convert File Excel to CSV")
	public void convertFile() {
		
		excelToCSV.convertExcelToCSV(pathExcel, "src\\test\\resources\\Template.csv");
		
	}// end convertFile

	
	@ParameterizedTest
	@Order(2)
	@DisplayName("B - Read Data CSV")
	@CsvFileSource(resources = "/Template.csv", numLinesToSkip = 1)
	public void readData(String NumReg) {
		
		String frameworkPath = "src/test/resources";
	    String targetFileName = "Template.csv";
	    File targetFile = Paths.get(frameworkPath, targetFileName).toFile();
	    // Espera hasta que el archivo exista
	    while (!targetFile.exists()) {
	        try {
	            // Espera 2 segundo antes de verificar nuevamente
	            Thread.sleep(2000);
	            
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    assertTrue(targetFile.exists(), "El archivo objetivo no se encuentra en la ruta especificada");
		
		int intNumReg = Integer.parseInt(NumReg);
		System.out.println("N° Iteration--->"+intNumReg);
		
		String Username = ExcelRead.readByColumnName(pathExcel, "Testing", "Username", intNumReg);
		String Password = ExcelRead.readByColumnName(pathExcel, "Testing", "Password", intNumReg);
		
		System.out.println("Username "+Username);
		System.out.println("Password "+Password);
		
	}// end readData
	
	
	

}// end JUExample2Test
