package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By headerlocator = By.tagName("h1");
	private By quantity = By.id("input-quantity");
	private By addtocart= By.id("button-cart");
	By productImages = By.cssSelector("ul.thumbnails img");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		String headertext = eleUtil.doElementgetText(headerlocator);
		System.out.println("the product header value :: " + headertext);
		return headertext;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("The images count :::: " + imagesCount);
		return imagesCount;
	}

	public void enterQuantity(int qty) {
		System.out.println("ProductQuantity" + qty);
		eleUtil.doSendkeys(quantity, String.valueOf(qty));

	}
	
	public void addProductToCart() {
		eleUtil.doClick(addtocart);
	}
}
