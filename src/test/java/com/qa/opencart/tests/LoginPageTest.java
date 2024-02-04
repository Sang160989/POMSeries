package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;

public class LoginPageTest extends BaseTest{
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actualtitle= lp.getLoginPageTitle();
		Assert.assertEquals(actualtitle, "Account Login");
	}

	@Test(priority=2)
	public void loginPageUrlTest() {
		String acturl =lp.getLoginPageUrl();
		Assert.assertTrue(acturl.contains("php?route=account/login"));
	}
	
	@Test(priority=3)
	public void forgotpwdlinkexist() {
		Assert.assertTrue(lp.isForgotPasswordLinkExist());
	}
	
	@Test(priority=4)
	public void doLoginTest() {
		accpage= lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accpage.isSearchExist());
	}
}
