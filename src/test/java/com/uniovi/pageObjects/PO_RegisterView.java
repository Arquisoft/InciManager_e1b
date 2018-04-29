package com.uniovi.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_RegisterView extends PO_NavView {
	
	static public void fillForm(WebDriver driver, String namep, String descriptionp, String locationp, String tagsp,
			String additionalInformationp, String propertiesp, String statep, String notificationp, String expirationp,
			String assignedTop) {
		WebElement name = driver.findElement(By.name("name"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement description = driver.findElement(By.name("description"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);
		WebElement location = driver.findElement(By.name("location"));
		location.click();
		location.clear();
		location.sendKeys(locationp);
		WebElement tags = driver.findElement(By.name("tags"));
		tags.click();
		tags.clear();
		tags.sendKeys(tagsp);
		WebElement additionalInformation = driver.findElement(By.name("additionalInformation"));
		additionalInformation.click();
		additionalInformation.clear();
		additionalInformation.sendKeys(additionalInformationp);
		WebElement properties = driver.findElement(By.name("properties"));
		properties.click();
		properties.clear();
		properties.sendKeys(propertiesp);
		WebElement state = driver.findElement(By.name("state"));
		state.click();
		state.clear();
		state.sendKeys(statep);
		WebElement notification = driver.findElement(By.name("notification"));
		notification.click();
		notification.clear();
		notification.sendKeys(notificationp);
		WebElement expiration = driver.findElement(By.name("expiration"));
		expiration.click();
		expiration.clear();
		expiration.sendKeys(expirationp);
		WebElement assignedTo = driver.findElement(By.name("assignedTo"));
		assignedTo.click();
		assignedTo.clear();
		assignedTo.sendKeys(assignedTop);
		
		// Pulsar el boton de Alta.
		By boton = By.name("btn");
		driver.findElement(boton).click();
	}
}
