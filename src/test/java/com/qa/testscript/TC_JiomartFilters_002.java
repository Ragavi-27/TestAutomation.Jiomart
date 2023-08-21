package com.qa.testscript;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_JiomartFilters_002 extends TestBase {
	// Checking if user can able to apply multiple filters at the same time on
	// groceries page
	@Test
	public void ApplyMultipleFilters() throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		SoftAssert softAssert = new SoftAssert();
		//Thread.sleep(2000);
		// Checking if the browser is on Groceries page
		
		JiomartOR.getGroceriesMenu().click();
		String GroceriesTitle = driver.getTitle();

		if (GroceriesTitle.contains("Groceries - JioMart")) {
			Assert.assertTrue(true, "Groceries page is opened");

			// Scrolling the page to see the filters option
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)");

			// Applying filters and checking if the filters are applied
			int AppliedFilters = 0;
			for (int i = 0; i < 3; i++) {
				String itemName = JiomartOR.getAnyCheckBox().getText();
				JiomartOR.getAnyCheckBox().click();
				Thread.sleep(2000);

				List<WebElement> CurrentRefinements = JiomartOR.getCurrentRefinements();
				for (WebElement item : CurrentRefinements) {
					if (item.getText().contains(itemName)) {
						softAssert.assertTrue(true);
						AppliedFilters += 1;
						System.out.println(itemName + " Filter is applied");
					}
					Thread.sleep(2000);
				}
			}
			if (AppliedFilters == 3) {
				softAssert.assertTrue(true);
				System.out.println("All the Filters are applied");
			} else {
				js.executeScript("window.scrollBy(0,-350)");
				captureScreenshot(driver, "ApplyMultipleFilters");
				softAssert.assertTrue(false);
				System.out.println("All the Filters are not applied");
			}
			softAssert.assertAll();
		} else {
			captureScreenshot(driver, "ApplyMultipleFilters");
			Assert.assertTrue(false, "Groceries page is not opened");
		}

	}

}