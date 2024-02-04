package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accpage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void getAccPageTitleTest() {
		String acttitle = accpage.getAccPageTitle();
		Assert.assertEquals(acttitle, "My Account");
	}

	@Test
	public void accPageUrlTest() {
		String acturl = accpage.getAccPageUrl();
		Assert.assertTrue(acturl.contains("route=account/account"));
	}

	@Test
	public void isSearchExistTest() {
		Assert.assertTrue(accpage.isSearchExist());
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook" }, { "imac" }, { "samsung" }, { "Apple" }

		};
	}

	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchkey) {
		searchPage = accpage.performSearch(searchkey);
		Assert.assertTrue(searchPage.getSearchProductsCount() > 0);
	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {

				{ "Macbook", "Macbook Air" }, { "iMac", "iMac" }, { "samsung", "Samsung Galaxy Tab 10.1" },
				{ "Apple", "Apple Cinema 30/" } };

	}

	@Test(dataProvider = "getProductTestData")
	public void searcProductTest(String searchkey, String prodName) {
		searchPage = accpage.performSearch(searchkey);
		if (searchPage.getSearchProductsCount() > 0) {
			productInfoPage = searchPage.selectProduct(prodName);
			String actProdheader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProdheader, prodName);
		}
	}

}
