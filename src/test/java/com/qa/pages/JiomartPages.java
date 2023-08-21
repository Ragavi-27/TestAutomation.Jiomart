package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiomartPages {
	WebDriver driver;
	
	@FindBy(id="nav_cat_2")
	WebElement GroceriesMenu;
	public WebElement getGroceriesMenu() {
		return GroceriesMenu;
	}
	
	@FindBy(id="nav_cat_5")
	WebElement BeautyMenu;
	public WebElement getBeautyMenu() {
		return BeautyMenu;
	}
	
	@FindAll(@FindBy(xpath="/html/body/div[1]/main/div[2]/div[2]/div[2]/span[2]/span"))
	List<WebElement> CurrentRefinements;
	public List<WebElement> getCurrentRefinements(){
		return CurrentRefinements;
	}
	
	@FindBy(xpath="/html/body/div[1]/main/div[2]/div[1]/div[2]/div/div/div[7]/div/ul/li[6]/div/div/label/span")
	WebElement AnyCheckBox;
	public WebElement getAnyCheckBox(){
		return AnyCheckBox;
	}
	
	@FindBy(xpath="/html/body/div[1]/main/div[2]/div[1]/div[2]/div/div/div[7]/div/div/div/form/input")
	WebElement BrandSearchBox;
	public WebElement getBrandSearchBox(){
		return BrandSearchBox;
	}
	
	@FindBy(xpath="/html/body/div[1]/main/div[2]/div[1]/div[2]/div/div/div[7]/div/ul/li[1]/div/div/label/span")
	WebElement BrandSearchResult;
	public WebElement getBrandSearchResult(){
		return BrandSearchResult;
	}
	
	@FindAll(@FindBy(className="filter-option-title"))
	List<WebElement> FilterOptions;
	public List<WebElement> getFilterOptions(){
		return FilterOptions;
	}
	
	
	@FindBy(xpath="/html/body/div[1]/main/div[2]/div[1]/div[2]/div/div/div[9]/div/div/div[2]")
	WebElement PriceFilterStart;
	public WebElement getPriceFilterStart(){
		return PriceFilterStart;
	}
	
	@FindBy(xpath="/html/body/div[1]/main/div[2]/div[1]/div[2]/div/div/div[9]/div/div/div[3]")
	WebElement PriceFilterEnd;
	public WebElement getPriceFilterEnd(){
		return PriceFilterEnd;
	}
			
	@FindBy(xpath="/html/body/div[1]/main/div[2]/div[1]/div[2]/div/div/div[11]/div/div/div[2]")
	WebElement DiscountFilterStart;
	public WebElement getDiscountFilterStart(){
		return DiscountFilterStart;
	}
	
	@FindBy(xpath="/html/body/div[1]/main/div[2]/div[1]/div[2]/div/div/div[11]/div/div/div[3]")
	WebElement DiscountFilterEnd;
	public WebElement getDiscountFilterEnd(){
		return DiscountFilterEnd;
	}
	
	public JiomartPages(WebDriver rDriver) {
		this.driver=rDriver;
		PageFactory.initElements(driver, this);
	}


}
