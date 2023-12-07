package ReadExcel;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setup() {
        // Set up WebDriver and Extent Reports
    	WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("path/to/extent-report.html");
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testExample() {
        // Your test code here
        driver.get("https://facebook.com");
        // Some test steps...
        // For demonstration purposes, let's consider a failing assertion
      String current_url=  driver.getCurrentUrl();
      String exp_url = "https:facebok.com";
        //org.testng.Assert.assertEquals(1, 2);
      Assert.assertEquals(current_url, exp_url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture screenshot if the test fails
            String screenshotPath = captureScreenshot(driver, result.getName());
            test.log(Status.FAIL, "Test Case Failed: " + result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed: " + result.getName());
        } else {
            test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        }

        driver.quit();
        extent.flush();
    }

    private String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            String dest = "path/to/screenshots/" + screenshotName + ".png";
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return dest;
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return e.getMessage();
        }
    }
}



