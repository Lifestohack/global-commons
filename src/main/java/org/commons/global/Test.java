package org.commons.global;

import java.util.List;

import org.commons.files.GlobalFiles;
import org.commons.converter.*;

public class Test {

	public static void main(String[] args) {	
//		FileToTest();
		
		
	}

	public static void FileToTest(){
		List<String> text = GlobalFiles.getEveryLinesFromFile("D:/Users/diwbhatt/Desktop/Temp/30199.txt");
		for (String ln : text){
			System.out.println(ln);
		}
		

	}
	
	
}
