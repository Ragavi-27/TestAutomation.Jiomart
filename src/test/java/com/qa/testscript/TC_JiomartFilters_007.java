package com.qa.testscript;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.utility.ExcelUtility;

public class TC_JiomartFilters_007 extends TestBase {

	@Test(dataProvider = "getData")
	public void BrandSearchOption(String searchTerm) throws InterruptedException, IOException {

		SoftAssert softAssert = new SoftAssert();
		

		// Opening Groceries page
		JiomartOR.getGroceriesMenu().click();
		String GroceriesTitle = driver.getTitle();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		if (GroceriesTitle.contains("Groceries - JioMart")) {
			Assert.assertTrue(true, "Groceries page is opened");

			// scrolling the page to go to the brand section
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,400)");

			// searching and selecting the brand item
			JiomartOR.getBrandSearchBox().sendKeys(searchTerm);
			JiomartOR.getBrandSearchBox().sendKeys(Keys.ENTER);

			// Checking the results
			if (driver.getPageSource().contains("No results")) {
				softAssert.assertTrue(true);
				System.out.println(searchTerm + " Brand is not avalaible in this category");
			} else if (JiomartOR.getBrandSearchResult().getText().contains(searchTerm)) {
				softAssert.assertTrue(true);
				System.out.println(searchTerm + " Brand Filter is applied");
			}
			softAssert.assertAll();
		} else {
			captureScreenshot(driver, "BrandSearchOption");
			Assert.assertTrue(false, "Groceries page is not opened");
		}
	}

	@DataProvider
	public String[][] getData() throws IOException {
		String XlPath = "C:\\Users\\RAGAVIP\\OneDrive - Virtusa\\Automation_EclipseWorkspace\\TestAutomation.Jiomart\\src\\test\\java\\com\\qa\\testdata\\TestData.xlsx";
		String XSheetName = "Sheet1";
		int rowCount = ExcelUtility.getRowCount(XlPath, XSheetName);
		int cellCount = ExcelUtility.getCellCount(XlPath, XSheetName, rowCount);
		String[][] data = new String[rowCount][cellCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				data[i - 1][j] = ExcelUtility.getCellData(XlPath, XSheetName, i, j);
			}
		}
		return data;
	}
}
