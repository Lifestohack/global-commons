package org.commons.converter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.commons.excel.DIExcelManager;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class DIFileConverter{

	public static void cSVToExcel(String fileToConvert, String savePath) {
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
	
	
	
	public static void excelToCSV(String fileToConvert, String savePath) {
		convert(fileToConvert, null, 0, savePath ); //Default sheet
	}
	
	public static void excelToCSV(String fileToConvert, int sheetIndex, String savePath) {
		convert(fileToConvert, null, sheetIndex, savePath );
		
	}

	public static void excelToCSV(String fileToConvert, String sheetName, String savePath) {
		convert(fileToConvert, sheetName, -1, savePath );
		
	}
	
	private static void convert(String fileToConvert, String sheetName, int sheetIndex, String savePath){
		DIExcelManager excelManager = new DIExcelManager();
		List<String> cellList = null;
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(savePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Workbook wb = excelManager.readExcel(fileToConvert);
		for(int i=0; i<=wb.getSheetAt(0).getLastRowNum(); i++){
			if(sheetName == null || sheetName.isEmpty()){
				if(sheetIndex < 0){
					sheetIndex = 0;
				}
				cellList = excelManager.getAllCellList(wb, sheetIndex, i); //First sheet as default
			}else{
				
				cellList = excelManager.getAllCellList(wb, sheetName, i);
				
			}
			
			String[] cellArray = new String[cellList.size()];
			cellList.toArray(cellArray);
			writer.writeNext(cellArray);
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		excelManager.close();
	}
}
