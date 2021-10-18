package com.hepsiburada.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends PageObject {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "txtUserName")
    private WebElement email_input;


    @FindBy(id = "txtPassword")
    private WebElement password_input;

    @FindBy(id = "btnLogin")
    private WebElement login_btn;

    //There are two ways to login. One way we are login with phone number or email.If so Try works. If not, error is catch and we are login with username and password.
    public void login(String email, String password) {
        try {
            driver.findElement(By.xpath("//div[text()='Yeni giriş yöntemi!']"));
            email_input.sendKeys(email);
            login_btn.click();
            password_input.sendKeys(password);
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(login_btn)).click();
            //login_btn.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            email_input.sendKeys(email);
            password_input.sendKeys(password);
            login_btn.click();
        }
    }

}
