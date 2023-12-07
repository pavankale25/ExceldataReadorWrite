package com.XLUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	static String excel_path="E:\\Java_Program\\PracticeProject\\TestData\\New XLSX Worksheet.xlsx";

	public XLUtility(){
		this.excel_path = excel_path;
	}
	public int getRowCount(String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream(excel_path); //no need for file object
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		wb.close();   
		fis.close();
		return rowCount;
	}
	
	public void writeExcelSheet(String sheetName, String cellValue, int row, int col) throws Exception {             
		FileInputStream fis = new FileInputStream(excel_path);    //to read excel file
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheet(sheetName);
	//	String name = sheet.getRow(0).getCell(0).getStringCellValue();      //Read data from Excel sheet
		sheet.getRow(row).createCell(col).setCellValue(cellValue);
		FileOutputStream fos = new FileOutputStream(new File(excel_path));
		wb.write(fos);
		wb.close();
		fos.close();
	}
	
	public int getCellCount(String sheetName, int row_num) throws Exception {
		FileInputStream fis = new FileInputStream(excel_path);    //to read excel file
		XSSFWorkbook wb = new XSSFWorkbook(fis);  
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFRow row = sheet.getRow(row_num);
		int cellcount = row.getLastCellNum();  //cell count
		wb.close();
		fis.close();
		return cellcount;
	}
	
	public void setCellData(String sheetName, int row, int col, String da) throws Exception {
		FileInputStream fis = new FileInputStream(excel_path);   
		XSSFWorkbook wb = new XSSFWorkbook(fis);
	//	wb.createSheet("New Emp");     //New Sheet Creation
	//	wb.removeSheetAt(2);           //Sheet remove
		XSSFSheet sheet = wb.getSheet(sheetName);
		sheet.getRow(row).createCell(col).setCellValue(da);
		FileOutputStream fos = new FileOutputStream(new File(excel_path));
		wb.write(fos);
		wb.close();
		fos.close();
	}
	
	//Read data from Excel sheet
	public static String getCellData(String sheetName,int rownum, int colnum ) throws Exception  { 
		FileInputStream fis = new FileInputStream(excel_path);    //to read excel file
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFCell cell = wb.getSheet(sheetName).getRow(rownum).getCell(colnum);
		wb.close();
		return cell.getStringCellValue();
	}
//mjjason d3 Each webpage will be handle in seprate class 
	//In a class we can write down the method on different class
	//How to run only fail test cases in testng
	//At the time automation testing if any test cases get fail then first we have to fresh that project
	//then fail.xml file is created then we have to run only that file
	
	//
}
