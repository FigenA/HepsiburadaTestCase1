package com.hepsiburada.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hepsiburada.conf.Driver;


public class MainPage extends PageObject {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "myAccount")
    private WebElement account_btn;

    @FindBy(id = "login")
    private WebElement login_btn;

    @FindBy(css = "input.desktopOldAutosuggestTheme-input")
    private WebElement search_input;

    @FindBy(className = "SearchBoxOld-buttonContainer")
    private WebElement search_btn;

    @FindBy(id = "shoppingCart")
    private WebElement basket_btn;

    @FindBy(className = "sf-OldMyAccount-1k66b")
    private WebElement userName;

    private void clickMyAccount() {
        account_btn.click();
        login_btn.click();
    }

    public LoginPage login() {
        clickMyAccount();
        return new LoginPage(driver);
    }

    public boolean checkUsername(String username) {
        return this.userName.getText().equals(username);
    }

    public BasketPage goToBasket() {
        basket_btn.click();
        return new BasketPage(driver);
    }

    private void search(String text) {
        search_input.sendKeys(text);
        search_btn.click();
    }


    public SearchResultsPage goToSearchPage(String searchText) {

        search(searchText);
        return new SearchResultsPage(driver);
    }
}