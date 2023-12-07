package Core;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	 protected static WebDriver driver;
	 public static ExtentSparkReporter sparkReport;   //To create report
	 //public static ExtentReporter htmlReporter;
	 public static ExtentReports extent;              //Add test cases in report
	 public static ExtentTest test;                   //Update the Status of test cases
	// public static ExtentSparkReporter spark;
		public static Date date = new Date();
		public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		public static String dt = formatter.format(date);
		public static String reportDestination = "reports/report_" + dt + ".html";
		
	 @BeforeSuite
	 public void setExtent() {  
		// Delete everything from reports folder expect .gitkeep
			File dir = new File(System.getProperty("user.dir") + "/reports");
			File[] files = dir.listFiles();
			for (File file : files) {
				// convert the file name into string
				String fileName = file.toString();
				int index = fileName.lastIndexOf('.');
				if (index > 0) {
					String extension = fileName.substring(index + 1);
					if (extension.equalsIgnoreCase("html") || extension.equalsIgnoreCase("zip")) {
						String filePath = fileName;
						File deleteFile = new File(filePath);
						deleteFile.delete();
					}
				}}
		 sparkReport = new ExtentSparkReporter(reportDestination);
		 sparkReport.config().setDocumentTitle("Automation Test Report");
		 sparkReport.config().setReportName("OrangeHRM Test Automation Report");
		 sparkReport.config().setTheme(Theme.DARK);
		 sparkReport.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	  
	  extent = new ExtentReports();
	  extent.attachReporter(sparkReport);
	  extent.setSystemInfo("HostName", "MyHost");
	  extent.setSystemInfo("ProjectName", "OrangeHRM");
	  extent.setSystemInfo("User name", "Pavan Kale");
	  extent.setSystemInfo("OS", "Win10");
	  extent.setSystemInfo("Browser", "Chrome");
			
	 }
	 
	 @AfterSuite
	 public void endReport() {
	  extent.flush();
	 }
	 
	 @BeforeMethod
	 public void setup() {
	   WebDriverManager.chromedriver().setup(); 
	   driver = new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	 }
	 
	 @AfterMethod
	 public void tearDown(ITestResult result) throws IOException {
	  if(result.getStatus()==ITestResult.FAILURE) {
	   test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	   test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	   
	   String pathString=Base.screenShot(driver, result.getName());
	   test.addScreenCaptureFromPath(pathString);
	   
	  } else if(result.getStatus()==ITestResult.SKIP) {
	   test.log(Status.SKIP, "Skipped Test case is: "+result.getName());
	  } else if(result.getStatus()==ITestResult.SUCCESS) {
	   test.log(Status.PASS, "Pass Test case is: "+result.getName());
	  }
	  driver.close();
	 } 
	 
	 public static String screenShot(WebDriver driver,String filename) {
			 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		        // Convert WebDriver instance to TakesScreenshot
		        TakesScreenshot screenshot = (TakesScreenshot) driver;
		        // Capture screenshot as a file
		        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		        // Define the destination path for the screenshot
		       String destination = System.getProperty("user.dir")+"\\ScreenShot\\"+filename+"_"+dateName+".png";
		        // Copy the file to the destination
		      
		        System.out.println("Screenshot of the failed test saved at: " + destination);
		        File finalDestination= new File(destination);
		        try {
		        	   FileUtils.copyFile(srcFile, finalDestination);
		        	  } catch (Exception e) {
		        	   // TODO Auto-generated catch block
		        	   e.getMessage();
		        	  }
		        	  return destination;
		        	 }
	 
}

