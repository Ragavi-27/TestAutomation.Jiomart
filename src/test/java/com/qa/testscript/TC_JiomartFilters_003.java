package com.qa.testscript;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_JiomartFilters_003 extends TestBase {
	// Checking if filter options are changing based on categories section
	@Test
	public void FilterOptions() throws InterruptedException, IOException {

		// checking the filter options of groceries category
		JiomartOR.getGroceriesMenu().click();
		String GroceriesTitle = driver.getTitle();
		
		if (GroceriesTitle.contains("Groceries - JioMart")) {
			Assert.assertTrue(true, "Groceries page is opened");
			Thread.sleep(3000);

			List<String> StringGroceriesFilter = new ArrayList<String>();
			List<WebElement> GroceriesFilterOptions = JiomartOR.getFilterOptions();
			for (WebElement item : GroceriesFilterOptions) {
				StringGroceriesFilter.add(item.getText());
			}

			// checking the filter options of beauty category
			JiomartOR.getBeautyMenu().click();
			String BeautyTitle = driver.getTitle();
			Assert.assertEquals(BeautyTitle, "Beauty - JioMart");
			Thread.sleep(3000);

			List<String> StringBeautyFilter = new ArrayList<String>();
			List<WebElement> BeautyFilterOptions = JiomartOR.getFilterOptions();
			for (WebElement item : BeautyFilterOptions) {
				StringBeautyFilter.add(item.getText());
			}

			// checking if filters are changing
			if(StringGroceriesFilter.containsAll(StringBeautyFilter)) {
				Assert.assertTrue(false, "Filter options are changing");
				System.out.println("Filter options are changing");
			}
			else {
				Assert.assertTrue(true, "Filter options are not changing");
				System.out.println("Filter options are not changing");
			}
			//Assert.assertNotEquals(StringBeautyFilter, StringGroceriesFilter, "Filter options are changing");

		} else {
			captureScreenshot(driver, "FilterOptions");
			Assert.assertTrue(false, "Groceries page is not opened");
		}
	}

}
