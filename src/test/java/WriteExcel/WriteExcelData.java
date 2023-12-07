package WriteExcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;

public class WriteExcelData {
	String excel_path="E:\\Java_Program\\PracticeProject\\TestData\\Heyy.xlsx";
	String sheet_name = "Sheet1";
	
	
	public void writeExcel(String sheetName, String cellValue, int row, int col) throws Exception {             
		FileInputStream fis = new FileInputStream(excel_path);    //to read excel file
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheet_name);
	//	String name = sheet.getRow(0).getCell(0).getStringCellValue();      //Read data from Excel sheet
		sheet.getRow(row).createCell(col).setCellValue(cellValue);
		FileOutputStream fos = new FileOutputStream(new File(excel_path));
		wb.write(fos);
		wb.close();
		fos.close();
	}


	public void addSheet(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setCellData(String sheetName, int row, int col, String da) throws Exception {
		FileInputStream fis = new FileInputStream(excel_path);    //to read excel file
		XSSFWorkbook wb = new XSSFWorkbook(fis);
	//	wb.createSheet("New Emp");     //New Sheet Creation
		wb.removeSheetAt(2);           //Sheet remove
		XSSFSheet sheet = wb.getSheet(sheet_name);
		sheet.getRow(row).createCell(col).setCellValue(da);
		FileOutputStream fos = new FileOutputStream(new File(excel_path));
		wb.write(fos);
		wb.close();
		fos.close();
	}
	

	

}
