package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataLibrary {
	
	public static String[][] readExcelValue(String file,String sheetName) throws IOException {
		
		XSSFWorkbook wb = new XSSFWorkbook("./data/"+file+".xlsx");
		XSSFSheet sheet = wb.getSheet(sheetName);
		int row = sheet.getLastRowNum();
		int cell = sheet.getRow(1).getLastCellNum();
		String data[][] = new String[row][cell];
		
		//fetch excel values
		for(int i=1;i<=row;i++) {
			for(int j=0;j<cell;j++) {
				data[i-1][j]= sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		wb.close();
		return data;
	}

}
