package com.qa.testscript;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_JiomartFilters_001 extends TestBase {
	// Checking if user can apply to apply filter on groceries page
	
	@Test
	public void ApplyFilter() throws InterruptedException, IOException {

		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Checking if the user is on Groceries page
		JiomartOR.getGroceriesMenu().click();
		String GroceriesTitle = driver.getTitle();
		if (GroceriesTitle.contains("Groceries - JioMart")) {
			Assert.assertTrue(true, "Groceries page is opened");

			// Scrolling the page to see the filters option
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)");

			// Applying filter and checking if the filter is applied
			String itemName = JiomartOR.getAnyCheckBox().getText();
			JiomartOR.getAnyCheckBox().click();

			List<WebElement> CurrentRefinements = JiomartOR.getCurrentRefinements();
			for (WebElement item : CurrentRefinements) {
				if (item.getText().contains(itemName)) {
					softAssert.assertTrue(true);
					System.out.println(itemName + " Filter is applied");
				} else {
					js.executeScript("window.scrollBy(0,-350)");
					captureScreenshot(driver, "ApplyFilter");
					softAssert.assertTrue(false);
					System.out.println(itemName + " Filter is not applied");
				}
			}
			softAssert.assertAll();
		} else {
			captureScreenshot(driver, "ApplyFilter");
			Assert.assertTrue(false, "Groceries page is not opened");
		}
	}

}
