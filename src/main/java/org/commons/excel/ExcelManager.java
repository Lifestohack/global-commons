package org.commons.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

	private Workbook wb = null;
	private Sheet sheet = null;
	
	
	private FileOutputStream out = null;
	
	public ExcelManager(){
		wb = new XSSFWorkbook();
		sheet = CreateSheet(wb);
	}
	
	public ExcelManager(String sheetName){
		wb = new XSSFWorkbook();
		sheet = CreateSheet(wb, sheetName);
	}
	
	public void WriteToRowExcel(Sheet sheetTemp, String[] rowItems, int rowIndex){
		if(sheetTemp == null){
			sheetTemp = this.sheet;
		}
		Row row = CreateRow(sheetTemp, rowIndex);
		for(int i = 0; i<=rowItems.length-1; i++){
			Cell cell = CreateCell(row, i);
			cell.setCellValue(rowItems[i]);
		}
	}

	
	public Row CreateRow(Sheet sheetTemp, int rowIndex){
		if(sheetTemp == null){
			sheetTemp = sheet;
		}
		return sheetTemp.createRow((short)rowIndex);
	}
	
	public Cell CreateCell(Row rowTemp, int cellIndex){
		return rowTemp.createCell(cellIndex);
	}
	
	public Sheet CreateSheet(Workbook tempWb){
		if(tempWb == null){
			tempWb = wb;
		}
		return tempWb.createSheet();
	}
	
	
	public Sheet CreateSheet(Workbook tempWb, String sheetName){
		if(tempWb == null){
			tempWb = wb;
		}
		return tempWb.createSheet(sheetName);
	}
	
	public void save(String path){
		try {
			out = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		
		try {
			wb.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
