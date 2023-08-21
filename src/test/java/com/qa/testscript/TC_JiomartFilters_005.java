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

public class TC_JiomartFilters_005 extends TestBase {
	@Test
	public void ApplyDiscountFilter() throws IOException, InterruptedException {

		// Checking if the browser is on Groceries page
		JiomartOR.getGroceriesMenu().click();
		String GroceriesTitle = driver.getTitle();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		if (GroceriesTitle.contains("Groceries - JioMart")) {
			Assert.assertTrue(true, "Groceries page is opened");

			SoftAssert softAssert = new SoftAssert();

			// Scrolling the page to see the filters option
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");

			// Changing the discount range
			Actions action = new Actions(driver);
			action.dragAndDropBy(JiomartOR.getDiscountFilterStart(), 50, 0).perform();
			String startingRange = JiomartOR.getDiscountFilterStart().getText();

			
			action.dragAndDropBy(JiomartOR.getDiscountFilterEnd(), -50, 0).perform();
			String EndRange = JiomartOR.getDiscountFilterEnd().getText();

			// Checking if the discount range filter is applied
			List<WebElement> currentRefinements = JiomartOR.getCurrentRefinements();
			int refinementsCount = 0;
			for (WebElement item : currentRefinements) {
				if (item.getText().contains(startingRange) || item.getText().contains(EndRange)) {
					refinementsCount += 1;
				}
			}
			if (refinementsCount != 2) {
				softAssert.assertTrue(true,"Discount range filter is applied");
				System.out.println("Discount range filter is applied");
			} else {
				captureScreenshot(driver, "ApplyDiscountFilter");
				softAssert.assertTrue(false,"Discount range filter is not applied");
				System.out.println("Discount range filter is not applied");
			}
			softAssert.assertAll();
		} else {
			captureScreenshot(driver, "ApplyDiscountFilter");
			Assert.assertTrue(false, "Groceries page is not opened");
		}
	}

}
