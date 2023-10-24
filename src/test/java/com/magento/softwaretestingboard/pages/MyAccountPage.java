package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage{

    private WebDriver driver;
    private WebDriverWait wait;

    private By successMessage = By.xpath("//*[contains(text(), 'Thank you for registering with Main Website Store.')]");
    private By mainSoreMenu = By.xpath("//*[@id='store.menu']");
    private By customerMenuLink = By.xpath("(//button[@class='action switch'])[1]");
    private By signOutLink = By.xpath("(//*[contains (text(), 'Sign Out')])[1]");
    private By accountContactInformation = By.xpath("//*[@class='box box-information']//*[@class='box-content']");


    public MyAccountPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }



    public String getSuccessMessage() {
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return confirmationMessage.getText();
    }


    public String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";

    // Method to compare the current URL with the expected URL
    public boolean isOnMyAccountPage() {
        // Get the actual URL
        String actualUrl = driver.getCurrentUrl();

        // Compare the actual URL with the expected URL
        return actualUrl.equals(expectedUrl);
    }

    public String isSignOutLinkVisible() {
        WebElement customerMenuDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(customerMenuLink));
        customerMenuDropDown.click();
        WebElement signOutLinkOnDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(signOutLink));
        return signOutLinkOnDropDown.getText();
    }

    public String homePageUrl = "https://magento.softwaretestingboard.com/";

    // Method to compare the current URL with the expected URL
    public boolean isOnHomePage() throws InterruptedException {
        // Get the actual URL
        String actualUrl = driver.getCurrentUrl();

        // Compare the actual URL with the expected URL
        Thread.sleep(1000);
        return actualUrl.equals(homePageUrl);
    }

    public boolean mainStoreMenuIsDisplayed() {
        WebElement storeMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(mainSoreMenu));
        return storeMenu.isDisplayed();
    }

    public String getAccountContactInformation() {
        WebElement accContactInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(accountContactInformation));
        return accContactInfo.getText();
    }
}