package com.uniovi.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView {

	static public void fillForm(WebDriver driver, String username, String passwordp) {
		WebElement dni = driver.findElement(By.name("ident"));
		dni.click();
		dni.clear();
		dni.sendKeys(username);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void identificar(WebDriver driver) {
		WebElement btn = driver.findElement(By.id("btnIdent"));
		btn.click();
	}
}
