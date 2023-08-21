package com.qa.testscript;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_JiomartFilters_004 extends TestBase {

	@Test
	public void ApplyPriceFilter() throws InterruptedException, IOException {

		// Checking if the browser is on Groceries page
		JiomartOR.getGroceriesMenu().click();
		String GroceriesTitle = driver.getTitle();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		if (GroceriesTitle.contains("Groceries - JioMart")) {
			Assert.assertTrue(true, "Groceries page is opened");
			SoftAssert softAssert = new SoftAssert();

			// Scrolling the page to see the filters option
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,450)");

			// Changing the price range
			Actions action = new Actions(driver);
			action.dragAndDropBy(JiomartOR.getPriceFilterStart(), 50, 0).perform();
			String startingPrice = JiomartOR.getPriceFilterStart().getText();

			action.dragAndDropBy(JiomartOR.getPriceFilterEnd(), -50, 0).perform();
			String EndingPrice = JiomartOR.getPriceFilterEnd().getText();

			// Checking if the price range filter is applied
			List<WebElement> currentRefinements = JiomartOR.getCurrentRefinements();
			int refinementsCount = 0;
			for (WebElement item : currentRefinements) {
				if (item.getText().contains(startingPrice) || item.getText().contains(EndingPrice)) {
					refinementsCount += 1;
				}
			}
			if (refinementsCount == 2) {
				softAssert.assertTrue(true,"Price range filter is applied");
				System.out.println("Price range filter is applied");
			} else {
				captureScreenshot(driver, "ApplyPriceFilter");
				softAssert.assertTrue(false,"Price range filter is not applied");
				System.out.println("Price range filter is not applied");
			}
			softAssert.assertAll();
		} else {
			captureScreenshot(driver, "ApplyPriceFilter");
			Assert.assertTrue(false, "Groceries page is not opened");
		}
	}

}
