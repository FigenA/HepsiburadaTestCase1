package com.hepsiburada.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageObject {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "addToCart")
    private WebElement basket_btn;

    @FindBy(id = "product-name")
    private WebElement product_name;

    @FindBy(xpath = "//*[text()='Sepete git']")
    private WebElement check_out_basket;


    public String getProductName() {
        return product_name.getText();
    }

    public void clickToBasket() {
        basket_btn.click();
        driver.navigate().refresh();
    }

    public BasketPage goToBasket() {
        check_out_basket.click();
        return new BasketPage(driver);
    }

}