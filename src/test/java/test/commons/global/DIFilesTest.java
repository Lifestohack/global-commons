package test.commons.global;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.commons.files.DIFiles;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DIFilesTest {

	@Test
	public void saveTextFileTest() {
		String textToSave = "test";
		try {
			DIFiles.saveasTextFile(textToSave, "C:\\Users\\Diwas Bhattarai\\diwas.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean fileExists = DIFiles.isValidFile("C:\\Users\\Diwas Bhattarai\\diwas.txt");
		assertEquals(fileExists, true);

		String textfromFile = DIFiles.getTextFromFile("C:\\Users\\Diwas Bhattarai\\diwas.txt", Charset.forName("UTF-8"));
		assertEquals(textfromFile, textToSave);
		
		
	}

}