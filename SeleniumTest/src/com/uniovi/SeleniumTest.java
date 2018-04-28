package com.uniovi;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

//Ordenamos las pruebas por el nombre del m�todo
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {
	
	static String PathFirefox = "Firefox46.win\\FirefoxPortable.exe";

	static WebDriver driver = getDriver(PathFirefox);
	static String URL_INCI = "http://localhost:8090";
	static String URL_AGENTS = "http://localhost:8090";
	
	public static WebDriver getDriver(String PathFirefox) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL_INCI);
	}
	
	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	@Test
	public void A_TEST() {
		
	}

}
