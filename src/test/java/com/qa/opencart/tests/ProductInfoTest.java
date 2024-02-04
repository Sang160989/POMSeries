package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accpage = lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] { { "Macbook", "Macbook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30/", 6 } };
	}

	@Test(dataProvider = "getProductImagesTestData")
	public void getProductImagesCountTest(String searchKey, String productName, int imagesCount) {

		searchPage = accpage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);

	}
	
	//hashmap- no order
	//linkedhashmap- maintains order
	//treemap- it is same as hashmap but gives in ascending order

}
