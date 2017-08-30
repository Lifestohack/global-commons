package org.commons.converter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.commons.excel.DIExcelManager;

import com.opencsv.CSVReader;

public class DIFileConverter{

	public static void CSVToExcel(String fileToConvert, String savePath) {
		CSVReader reader = null;
		DIExcelManager excelManager = new DIExcelManager();
		try {
			   reader = new CSVReader(new FileReader(fileToConvert));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String [] nextLine;
		int rowIndex = 0;
	     try {
			while ((nextLine = reader.readNext()) != null) {
				excelManager.WriteToRowExcel(null, nextLine, rowIndex);
				rowIndex++;
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	     excelManager.save(savePath);
	     excelManager.close();
	}
	
	
	
	public static void ExcelToCSV(String fileToConvert, String savePath) {
		
	}
	

}
