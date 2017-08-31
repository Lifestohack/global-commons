package org.commons.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.commons.logger.DILogger;

public class DIExcelManager {

	private Workbook wb = null;
	private Sheet sheet = null;
	
	
	
	 private static final Logger logger =
		        Logger.getLogger(DILogger.class.getName());
	
	
	private FileOutputStream out = null;
	
	public DIExcelManager(){
		wb = new XSSFWorkbook();
		sheet = CreateSheet(wb);
	}
	
	public DIExcelManager(String sheetName){
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
	
	public Workbook readExcel(String path){
		
	    InputStream inp = null;
	    Workbook wb = null;
		try {
			inp = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	    try {
			wb = WorkbookFactory.create(inp);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return wb;
	}
	
	
	
	public List<String> getAllCellList(Workbook wb, int sheetIndex, int RowIndex){
			Sheet sheet = wb.getSheetAt(sheetIndex);
		    Row row = sheet.getRow(RowIndex);
		    List<String> cellValues = new ArrayList<String>();
		    for(Cell cell: row){
		    	cellValues.add(cell.toString());
		    }
		    return cellValues;
	}
	

	
	
	public List<String> getAllCellList(Workbook wb, String sheetName, int RowIndex) {
		Sheet sheet = wb.getSheet(sheetName);
		if(sheet == null){
			try {
				throw new Exception("Sheet " + sheetName + " doesnot exists.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    Row row = sheet.getRow(RowIndex); 
	    List<String> cellValues = new ArrayList<String>();
	    for(Cell cell: row){
	    	cellValues.add(cell.toString());
	    }
	    
	    
	    return cellValues;
}

	
	public List<String> getColumn(){
		return null;
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
