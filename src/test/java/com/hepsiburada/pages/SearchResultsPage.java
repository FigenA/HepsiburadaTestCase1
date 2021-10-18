package com.hepsiburada.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends PageObject {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='categorySuggestionList']/div/h1")
    private WebElement searchedText;


    public boolean checkSearchedText(String searchText) {
        return searchedText.getText().equals(searchText);
    }

    public ProductDetailsPage goToProductDetails(int index) {
        clickSearchedItem(index);
        return new ProductDetailsPage(driver);
    }

    private List<WebElement> getSearchedList() {
        List<WebElement> searchedList = driver.findElements(By.xpath("//li[contains(@class,'search-item')]"));

        return searchedList;
    }

    public boolean checkedSearchedList() {
        if (getSearchedList().size() > 0)
            return true;
        else
            return false;
    }

    private void clickSearchedItem(int index) {
        getSearchedList().get(index).click();
    }
}