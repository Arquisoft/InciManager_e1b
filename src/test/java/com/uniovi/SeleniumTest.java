package com.uniovi;

import org.junit.runners.MethodSorters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.pageObjects.PO_HomeView;
import com.uniovi.pageObjects.PO_LoginView;
import com.uniovi.pageObjects.PO_RegisterView;
import com.uniovi.pageObjects.PO_View;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(SeleniumTest.class)
// Ordenamos las pruebas por el nombre del m�todo
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {
//	static WebDriver driver = new FirefoxDriver();
//	static String URL_INCI = "http://localhost:8090";
//
//
//	@Before
//	public void setUp() throws Exception {
//		driver.navigate().to(URL_INCI);
//	}
//
//	@BeforeClass
//	static public void begin() {
//	}
//
//	@AfterClass
//	static public void end() {
//		driver.quit();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		driver.manage().deleteAllCookies();
//	}
//
//	// Probamos a entrar con un usuario incorrecto
//	@Test
//	public void A_TEST() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		PO_LoginView.fillForm(driver, "123", "123456");
//		PO_View.checkElement(driver, "text", "Entrar");
//	}
//
//	// Probamos a entrar con una contraseña incorrecta
//	@Test
//	public void B_TEST() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		PO_LoginView.fillForm(driver, "12345678P", "13456asaqwe");
//		PO_View.checkElement(driver, "text", "Entrar");
//	}
//
//	// Probamos entrar correctamente
//	@Test
//	public void C_TEST() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		PO_LoginView.fillForm(driver, "12345678P", "123456");
//		PO_View.checkElement(driver, "text", "Enviar");
//	}
//
//	// Probamos a registrar una incidencia
//	@Test
//	public void D_TEST() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		PO_LoginView.fillForm(driver, "12345678P", "123456");
//		PO_View.checkElement(driver, "text", "Enviar");
//
//		PO_RegisterView.fillForm(driver, "INCI1", "humedades", "40,-40", "humedad1,humedad2", "www.humedad.com",
//				"P0:r1", "2018-10-25", "1");
//
//		PO_View.checkElement(driver, "text", "INCI1");
//		PO_HomeView.clickOption(driver, "logout", "class", "");
//		PO_View.checkElement(driver, "text", "Identificate");
//
//	}
//
//	// Probamos a registrar una incidencia
//	@Test
//	public void E_TEST() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		PO_LoginView.fillForm(driver, "12345678P", "123456");
//		PO_View.checkElement(driver, "text", "Enviar");
//
//		PO_RegisterView.fillForm(driver, "INCI1", "humedades", "40,-40", "humedad1,humedad2", "www.humedad.com", "P0r1", "2018-10-25", "1");
//
//		PO_View.checkElement(driver, "text", "Ha ocurrido el siguiente error");
//
//	}
//
//	// Probamos a registrar una incidencia sin localizacion
//	@Test
//	public void F_TEST() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		PO_LoginView.fillForm(driver, "12345678P", "123456");
//		PO_View.checkElement(driver, "text", "Enviar");
//
//		PO_RegisterView.fillForm(driver, "INCI1", "humedades", "", "humedad1,humedad2", "www.humedad.com", "P0:r1", "2018-10-25", "1");
//
//		PO_View.checkElement(driver, "text", "INCI1");
//		PO_HomeView.clickOption(driver, "logout", "class", "");
//		PO_View.checkElement(driver, "text", "Identificate");
//
//	}
//	
//	// Probamos a registrar una incidencia mal sin localizacion
//	@Test
//	public void G_TEST() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		PO_LoginView.fillForm(driver, "12345678P", "123456");
//		PO_View.checkElement(driver, "text", "Enviar");
//
//		PO_RegisterView.fillForm(driver, "INCI1", "humedades", "", "humedad1, humedad2 ,humedad3", "www.humedad.com", "P0:r1", "2018-10-25", "1");
//
//		PO_View.checkElement(driver, "text", "Ha ocurrido el siguiente error");
//	}
//	
//	// Probamos a registrar una incidencia con campos vacios
//	@Test
//	public void H_TEST() {
//		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
//		PO_LoginView.fillForm(driver, "12345678P", "123456");
//		PO_View.checkElement(driver, "text", "Enviar");
//
//		PO_RegisterView.fillForm(driver, "INCI1", "humedades", "", "", "www.humedad.com", "P0:r1", "2018-10-25", "1");
//		PO_View.checkElement(driver, "text", "Datos de la incidencia");
//	}
	
	@Test
	public void emptyTest() {
		
	}

}
