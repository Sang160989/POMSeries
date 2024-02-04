package com.qa.opencart.driverfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initiDriver(Properties prop) {

		optionsManager = new OptionsManager(prop);

		String browsername = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("The browser name is " + browsername);
		if (browsername.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browsername.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browsername.equalsIgnoreCase("edge")) {
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		} else {
			System.out.println("pass the correct browser");
		}

		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		prop = new Properties();

		// mvn clean install -Denv
		String envName = System.getProperty("env");
		FileInputStream ip = null;
		try {
			if (envName == null) {
				System.out.println("no env is passed:: Running tests on qa env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
					throw new FrameworkException("Wrong env is passed");

				}
			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}

	/*
	 * get the local thread copy of the driver
	 */
	

}
