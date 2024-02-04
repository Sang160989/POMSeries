package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By Locators
	private By logoutLink = By.linkText("logout");
	private By search = By.name("search");
	private By searchIcon= By.cssSelector("#search button");
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title=eleUtil.waitForTitleIsAndFetch(10, "My Account");
		System.out.println(title);
		return title;
	}
	
	public String getAccPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(10, "route=account/account");
		System.out.println(url);
		return url;
		
	}
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, 10).isDisplayed();
	}
	
	public SearchPage performSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendkeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("search icon is not available");
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
