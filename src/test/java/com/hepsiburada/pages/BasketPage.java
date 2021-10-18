package com.hepsiburada.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends PageObject {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='basket_headerTop_15H0U']/h1")
    private WebElement basket_header;

    @FindBy(id = "continue_step_btn")
    private WebElement shopping_btn;


    public String getBasketHeader() {
        return basket_header.getText();
    }

    public PaymentPage gotToPayment() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(shopping_btn)).click();
        return new PaymentPage(driver);
    }
}