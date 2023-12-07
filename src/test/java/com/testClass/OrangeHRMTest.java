package com.testClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Core.Base;

public class OrangeHRMTest extends Base {
	@Test
	 public void loginPageTest() {
	  test=extent.createTest("loginPageTest");
	  WebElement imgElement=driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img"));
	  Assert.assertTrue(imgElement.isDisplayed());
	 }
	 
	 @Test
	 public void loginTest() throws InterruptedException {
	  test=extent.createTest("loginTest");
	  driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
	  driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")).click();
	  Thread.sleep(7000);
	        String actualTitle=driver.getTitle();
	        System.out.println("Actual title is" + actualTitle);
	        String expectedTitle="OrangeHRM";
	        Assert.assertEquals(actualTitle, expectedTitle);
	 }

	 @Test
	 public void sampleCase() {
	  test=extent.createTest("sampleCase");
	  test.createNode("Validation1");
	  Assert.assertTrue(true);
	  test.createNode("Validation2");
	  Assert.assertTrue(true);
	  test.createNode("Validation3");
	  Assert.assertTrue(true);
	  test.createNode("Validation4");
	  Assert.assertTrue(true);
	 }
}
