package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutSuccessPage {
    WebDriver driver;
    WebDriverWait wait;

    private final By ThankYouForPurchaseMessage = By.xpath("//*[contains(text(), 'Thank you for your purchase!')]");
    private final By continueShoppingButton = By.xpath("//span[contains(text(), 'Continue Shopping')]");
    private final By printReceiptLink = By.xpath("//*[contains(text(), 'Print receipt')]");


    public CheckoutSuccessPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    private final String expectedSuccessURL = "https://magento.softwaretestingboard.com/checkout/onepage/success/";

    // Method to compare the current URL with the expected URL
    public boolean isOnSuccessPage() {
        // Get the actual URL
        wait.until(ExpectedConditions.urlToBe(expectedSuccessURL));
        String actualUrl = driver.getCurrentUrl();

        // Compare the actual URL with the expected URL
        return actualUrl.equals(expectedSuccessURL);
    }

    public String getSuccessfulPurchaseMessage() {
        WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(ThankYouForPurchaseMessage));
        return thankYouMessage.getText();
    }

    public String confirmContinueButtonPresent() {
        WebElement continueShoppingBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton));
        return continueShoppingBtn.getText();
    }

    public String confirmPrintReceiptLinkPresent(){
        WebElement printLink = wait.until(ExpectedConditions.visibilityOfElementLocated(printReceiptLink));
        return printLink.getText();
    }
}
