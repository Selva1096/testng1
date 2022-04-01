package com.flipkart.stepdefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginValidation {
	static WebDriver driver;
	static long st;
	static JavascriptExecutor j;
	
	@DataProvider (name ="mobileNames")
	public Object[][] getMobNames() {
		return new Object[][] {{"realme","samsung"}};
	}

	@BeforeClass(groups="default")
	public static void bc() {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@AfterClass(groups="default")
	public static void ac() {
		//driver.quit();
	}
	@BeforeMethod
	public void startsAt() {
		st = System.currentTimeMillis();
	}
	@AfterMethod 
	public void endsAt(){
		long et = System.currentTimeMillis();
		System.out.println("Time Taken:" + (et-st));
	}
	@Parameters({"username","password"})
	@Test(priority=1, enabled =false)
	public void login (String user, String pass) {
		WebElement userName = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		userName.sendKeys(user);
		WebElement passWord = driver.findElement(By.xpath("//input[@type='password']"));
		passWord.sendKeys(pass);
	}
	@Test(priority =2)
	public void method1() {
		WebElement btn = driver.findElement(By.xpath("//button[contains(@class,'2doB4z')]"));
		btn.click();
	}
	@Test(priority=3, dataProvider = "mobileNames")
	public void search (String mob1, String mob2) {
		WebElement srch = driver.findElement(By.xpath("//input[@type='text']"));
		srch.sendKeys(mob2,Keys.ENTER);
	}
		
}
