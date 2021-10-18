package com.hepsiburada.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage extends PageObject {
	public PaymentPage(WebDriver driver) {
		super(driver);
	}
	
	 @FindBy(xpath = "//div[@class='shipping_container_2ZcEy']/h2")
	    private WebElement shipping_header;
	 
	 @FindBy(xpath = "//div[@class=' sardesPaymentPage-index-payment_methods_container']/h2")
	    private WebElement payment_header;
	 
	 @FindBy(id = "continue_step_btn")
	    private WebElement next_btn;
	 
	 @FindBy(xpath = "//*[@class='basket_summary_rGuor']/header/h2")
	    private WebElement basket_summary_header;
	 
	 @FindBy(xpath = "//div[@id='agreement_check']/label/input")
	    private WebElement agreement_btn;
	
	 
	 public String checkShippingHeader() {
		 return shipping_header.getText();
	 }
	 
	 public String checkPaymentHeader() {
		 return payment_header.getText();
	 }
	 
	 public String checkBasketSummary() {
		 return basket_summary_header.getText();
	 }
	 
	 public void clickNextStep() {
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(next_btn)).click();
		
	 }
	 
	 public void selectPaymentMethod(String paymentMethod,String bank){
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(String.format("//h3[text()='%s']", paymentMethod))))).click();
		WebElement bankElement=driver.findElement(By.xpath(String.format("//*[text()='%s']", bank)));
		bankElement.click();
		driver.findElement(By.xpath("//*[text()='Anında havale yöntemi ile öde ']")).click();
		
	    }
	 
	 public BankPage finish() {
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(agreement_btn)).click();
		 next_btn.click();
		 return new BankPage(driver);
				 }
}
	 