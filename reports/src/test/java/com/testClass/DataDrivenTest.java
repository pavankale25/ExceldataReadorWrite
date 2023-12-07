package com.testClass;

import org.openqa.selenium.By;

import Core.Base;

public class DataDrivenTest extends Base {
	
	public void loginTest(String userName, String password) {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")).click();
		
	}

}
