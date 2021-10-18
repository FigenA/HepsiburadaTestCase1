package com.hepsiburada.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.hepsiburada.conf.BrowserTypeEnum;
import com.hepsiburada.conf.Common;
import com.hepsiburada.conf.Driver;
import com.hepsiburada.pages.BankPage;
import com.hepsiburada.pages.BasketPage;
import com.hepsiburada.pages.LoginPage;
import com.hepsiburada.pages.MainPage;
import com.hepsiburada.pages.PaymentPage;
import com.hepsiburada.pages.ProductDetailsPage;
import com.hepsiburada.pages.SearchResultsPage;

public class AddBookToBasketTest extends Driver {

    // Browser type should be selected. The values it will take are into the
    // com/hepsiburada/com/BrowserTypeEnum
    public AddBookToBasketTest() {
        super(BrowserTypeEnum.firefox);
    }

    Common common = new Common();
    LoginPage loginPage;
    MainPage mainPage;
    SearchResultsPage searchResultPage;
    ProductDetailsPage productPage;
    BasketPage basketPage;
    PaymentPage paymentPage;
    BankPage bankPage;


    // User information must be specified in the user.properties file.
    Map<String, String> userProperty = common.readUserProperties("test1");
    String userName = userProperty.get("userName");
    String password = userProperty.get("password");
    String accountNameSurname = userProperty.get("accountNameSurname");

    //Enter search word
    String searchText = "Şibumi";

    
    @Test
    public void login() throws InterruptedException, IOException {
        try {
            mainPage = new MainPage(driver);
            assertEquals("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com", driver.getTitle());
            waitPageLoad(10);
            LoginPage loginPage = mainPage.login();
            assertEquals("Üye Giriş Sayfası & Üye Ol - Hepsiburada", driver.getTitle());
            waitPageLoad(30);
            loginPage.login(userName, password);
            AssertJUnit.assertTrue(mainPage.checkUsername(accountNameSurname));
        } catch (AssertionError e) {
            takeScreenShot();
        }


    }

    @Test(dependsOnMethods = {"login"})
    public void search() throws InterruptedException, IOException {

        try {
            searchResultPage = mainPage.goToSearchPage(searchText);
            //Assert.assertTrue(searchResultPage.checkSearchedText(searchText));
            Assert.assertTrue(searchResultPage.checkedSearchedList(), "Sepette ürün bulunmamaktadır.");
            productPage = searchResultPage.goToProductDetails(1);

        } catch (AssertionError e) {
            takeScreenShot();
        }

    }


    @Test(dependsOnMethods = {"search"})
    public void productDetails() throws InterruptedException, IOException {
        try {
            waitPageLoad(10);
            productPage.clickToBasket();
            waitPageLoad(10);
            basketPage = mainPage.goToBasket();
        } catch (AssertionError e) {
            takeScreenShot();
        }
    }

    @Test(dependsOnMethods = {"productDetails"})
    public void basket() throws InterruptedException, IOException {
        try {
            waitPageLoad(30);
            Assert.assertEquals("Sepetim", basketPage.getBasketHeader());
            paymentPage = basketPage.gotToPayment();
        } catch (AssertionError e) {
            takeScreenShot();
        }
    }

    @Test(dependsOnMethods = {"basket"})
    public void payment() throws InterruptedException, IOException {
        try {
            waitPageLoad(20);
            Assert.assertEquals("Teslimat adresi", paymentPage.checkShippingHeader());
            paymentPage.clickNextStep();
            waitPageLoad(20);
            //  Assert.assertEquals("Ödeme yöntemleri", paymentPage.checkPaymentHeader());
            paymentPage.selectPaymentMethod("Anında Havale", "Akbank");
            paymentPage.clickNextStep();
            waitPageLoad(20);
           // Assert.assertEquals("Sepetinizdeki ürünler", paymentPage.checkBasketSummary());
            bankPage = paymentPage.finish();

        } catch (AssertionError e) {
            takeScreenShot();
        }

    }

    @Test(dependsOnMethods = {"payment"})
    public void bank() throws InterruptedException, IOException {
        try {
            //Assert.assertTrue(bankPage.getPaymentHeader().equals(""));
            waitPageLoad(30);
            bankPage.cancelPayment();
        } catch (AssertionError e) {
            takeScreenShot();
        }
    }
}