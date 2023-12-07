package ReadExcel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
public class ReadeExcelData {

	WebDriver driver;
	String FilePath ="C:\\Users\\IPS-52\\Downloads\\Nomadic_care_QA.xlsx";
	String FileName = "Nomadic_care_QA";
	String SheetName = "Timepass";
	
	//@Test
	public void Read() throws IOException {  //Read Excel sheet Data
	FileInputStream fis = new FileInputStream(FilePath);  //To read Excel Files
	XSSFWorkbook wb = new XSSFWorkbook(fis);   //Fix that files
	int total_number = wb.getNumberOfSheets();  //total number of sheets
	System.out.println("Number of sheet" + total_number);
	XSSFSheet sheet = wb.getSheet(SheetName);                                          
	int RowCount = sheet.getLastRowNum();   // Total number of rows
	System.out.println("Total row count" + " " + RowCount);   
	for (int i=0;i<=RowCount;i++) {
		Row row = sheet.getRow(i);
		for (int j=0;j<row.getLastCellNum();j++) {
			//String Data1 = sheet.getRow(i).getCell(j).getStringCellValue();	//get data
			Cell cell = row.getCell(j);
			
			switch(cell.getCellType()) {
			case STRING:
				System.out.print(cell.getStringCellValue());break;        
				
			case NUMERIC:
				System.out.print(cell.getNumericCellValue());break;
				
			case BOOLEAN:
				System.out.print(cell.getBooleanCellValue());break;
			
				
			}
			System.out.print(" | ");
		}
		System.out.println();
		
	}

	wb.close();
	
	}
	@Test
	public void write() throws IOException {
		FileInputStream fis = new FileInputStream(FilePath);  //To read Excel Files
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheet("Timepass");
		sheet.getRow(0).createCell(1).setCellValue("Amol");
		FileOutputStream fos = new FileOutputStream(new File(FilePath));
		wb.write(fos);
		wb.close();
		fos.close();
	}

}
