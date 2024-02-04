package com.qa.opencart.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage lp;
	protected AccountPage accpage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop= df.initProp();
		driver = df.initiDriver(prop);
		lp= new LoginPage(driver);
	}

	@AfterTest
	public void teardown() {
		driver.quit();

	}

}
