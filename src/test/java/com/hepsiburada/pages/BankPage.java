package com.hepsiburada.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BankPage extends PageObject {
	public BankPage(WebDriver driver) {
		super(driver);
	}
	
	 @FindBy(className = "headertext")
	    private WebElement payment_header;
	 
	 @FindBy(xpath = "//*[contains(@class,'icon-logout')]")
	    private WebElement cancel_payment;
	 
	 public String getPaymentHeader() {
		 return payment_header.getText();
	 }
	 
	 public void cancelPayment() throws InterruptedException {
		 new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(cancel_payment)).click();
		 new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Evet']"))).click();
	 }
}