package org.commons.global;

import java.util.List;
import java.util.logging.Logger;

import org.commons.constants.DIConstants;
import org.commons.files.DIFiles;
import org.commons.logger.DILogger;
import org.commons.properties.DIProperties;

public class Test {
	
	 private static final Logger logger =
		        Logger.getLogger(DILogger.class.getName());

	public static void main(String[] args) {	
		
		String s = DIProperties.getInstance().getProperty("diwas");
		System.out.println(s);
//		FileToTest();
	}

	public static void FileToTest(){

		List<String> text = DIFiles.getEveryLinesFromFile("D:/Users/diwbhatt/Desktop/Temp/30199.txt");
		int i = 1;
		for (String ln : text){
			System.out.println(ln);
			logger.info(Integer.toString(i));
		i++;
		}
		

	}
	
	
}
