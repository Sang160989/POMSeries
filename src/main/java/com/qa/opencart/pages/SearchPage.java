package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchresults = By.cssSelector("div#content div.product-layout");

	public int getSearchProductsCount() {
		int prodcount= eleUtil.waitForElementsVisible(searchresults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product count ::::::: " + prodcount);
		return prodcount;
	}

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public ProductInfoPage selectProduct(String productname) {
		By productlocator = By.linkText(productname);
		eleUtil.waitForElementVisible(productlocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}
}
