package com.qa.testscript;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.qa.pages.JiomartPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	WebDriver driver;
	JiomartPages JiomartOR; 
	
	@Parameters({"Browser","Url"})
	@BeforeClass
	public void setUp(String Browser, String Url) {
		if(Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(Browser.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		
		JiomartOR=new JiomartPages(driver);
		driver.manage().window().maximize();
		driver.get(Url);
	}
	
 	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
 	
 	public void captureScreenshot(WebDriver driver, String testName) throws IOException {
 		TakesScreenshot ts=(TakesScreenshot)driver;
 		File source=ts.getScreenshotAs(OutputType.FILE);
 		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+testName+".png");
 		FileUtils.copyFile(source, target);
 		
 		
 	}

}