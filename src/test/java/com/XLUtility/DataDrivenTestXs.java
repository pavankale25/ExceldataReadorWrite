package com.XLUtility;

import java.time.Duration;
import com.XLUtility.XLUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDrivenTestXs {
WebDriver driver; 
	
	@BeforeClass
	public void setUp() {            //xutility file - dataprovider 
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	
	@Test(dataProvider="TestData")
	public void loginTest(String user, String pass, String exp) throws InterruptedException {
		driver.get("https://admin-demo.nopcommerce.com/login");
		WebElement txt_email = driver.findElement(By.id("Email"));
		txt_email.clear();
		txt_email.sendKeys(user);
	    WebElement txt_pass = driver.findElement(By.id("Password"));
		txt_pass.clear();
		txt_pass.sendKeys(pass);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
		String act_title = driver.getTitle();
		System.out.println("Actual_title" + act_title);
		String exp_title = "Dashboard / nopCommerce administration";
		
		if(exp.equals("Valid")) 
			{
				if(exp_title.equals(act_title)) {
					driver.findElement(By.linkText("Logout")).click();
					Assert.assertTrue(true);
					//Assert.assertEquals(act_title, exp_title);
				}
				else {
					Assert.assertTrue(false); 
				}
			}
			else if(exp.equals("Invalid")) {
				if(exp_title.equals(act_title)) {
					driver.findElement(By.linkText("Logout")).click();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
		}
	
	@DataProvider(name="TestData")
	public String [][] getData() throws Exception {
		String excel_path = "E:\\Java_Program\\PracticeProject\\TestData\\New XLSX Worksheet.xlsx";
		XLUtility xutil = new XLUtility();
		int totalRow = xutil.getRowCount("Sheet1");
		int totalcol = xutil.getCellCount("Sheet1", 1);
		System.out.println("Total row" + totalRow);
		String loginData [][]=new String [totalRow][totalcol];
		
		for(int i = 1;i<=totalRow;i++) {
			
			for(int j= 0; j<totalcol;j++) {
				loginData[i-1][j] =xutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
