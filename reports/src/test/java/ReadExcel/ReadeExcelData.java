package ReadExcel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
public class ReadeExcelData {

	WebDriver driver;
	String FilePath ="C:\\Users\\IPS-52\\Downloads\\Nomadic_care_QA.xlsx";
	String FileName = "Nomadic_care_QA";
	String SheetName = "Timepass";
	
	@Test
	public void Read() throws IOException {  //Read Excel sheet Data
	FileInputStream fis = new FileInputStream(FilePath);  //To read Excel Files
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	int total_number = wb.getNumberOfSheets();  //total number of sheets
	System.out.println(total_number);
	XSSFSheet sheet = wb.getSheet(SheetName);     
	int RowCount = sheet.getLastRowNum();   // Total number of rows
	System.out.println("Total" + " " + RowCount);
	for (int i=0;i<=RowCount;i++) {
		Row row = sheet.getRow(i);
		for (int j=0;j<row.getLastCellNum();j++) {
			String Data1 = sheet.getRow(i).getCell(j).getStringCellValue();	//get data
			System.out.print(" "+ Data1);
		}
		System.out.println();
		
	}
	
	wb.close();
	
	}

}
