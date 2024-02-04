package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private ElementUtil eleUtil;
	private WebDriver driver;
	// private By locators
	private By emailid = By.id("input-email");
	private By pwd = By.id("input-password");
	private By submit = By.xpath("//input[@value='Login']");
	private By forgotpwdlink = By.linkText("Forgotten Password");

	// public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// page actions/methods
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("The login page title is " + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_FRACTIONURL_VALUE);
		System.out.println("The page url is " + url);
		return url;
	}

	public boolean isForgotPasswordLinkExist() {
		return eleUtil.waitForElementVisible(forgotpwdlink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}

	public AccountPage doLogin(String email, String pwsd) {
		eleUtil.waitForElementVisible(emailid, 10).sendKeys("qatestertest@gmail.com");
		eleUtil.doSendkeys(pwd, "Test@123");
		eleUtil.doClick(submit);
		return new AccountPage(driver);
	}

}
