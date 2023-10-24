package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPaymentsPage {
    WebDriver driver;
    WebDriverWait wait;


    private By placeOrderButton = By.xpath("//span[contains(text(), 'Place Order')]");
    private By shippingAddress = By.xpath("//*[@class='ship-to']/descendant::*[@class='shipping-information-content']");
    private By itemsInCartDropDownBtn = By.xpath("//*[@id='opc-sidebar']/div[1]/div/div[1]");
    private By itemsInCartTotal = By.xpath("//*[@id='opc-sidebar']/div[1]/div/div[1]/strong/span[1]");
    private By productOnePricePaymentScreen = By.xpath("(//*[@class='cart-price'])[1]");
    private By productTwoPricePaymentScreen = By.xpath("(//*[@class='cart-price'])[2]");
    private By cartSubtotalPrc = By.xpath("//*[@data-th='Cart Subtotal']");
    private By shippingPrice = By.xpath("//*[@data-th='Shipping']");
    private By orderTotalPrice = By.xpath("(//*[@class='price'])[5]");

    public CheckoutPaymentsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    private String expectedPaymentURL = "https://magento.softwaretestingboard.com/checkout/#payment";

    // Method to compare the current URL with the expected URL
    public boolean isOnPaymentPage() {
        // Get the actual URL
        wait.until(ExpectedConditions.urlToBe(expectedPaymentURL));
        String actualUrl = driver.getCurrentUrl();

        // Compare the actual URL with the expected URL
        return actualUrl.equals(expectedPaymentURL);
    }

    public String getTotalItemsInCart(){
        WebElement totalItemsInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(itemsInCartTotal));
        return totalItemsInCart.getText();
    }

    public String getCartSubtotalPrice(){
        WebElement subtotalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(cartSubtotalPrc));
        return subtotalPrice.getText();
    }
    public String getShippingPrice(){
        WebElement shippingMethodePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingPrice));
        return shippingMethodePrice.getText();
    }
    public String getOrderTotalPrice(){
        WebElement orderTotalPrc = wait.until(ExpectedConditions.visibilityOfElementLocated(orderTotalPrice));
        return orderTotalPrc.getText();
    }
    public String getShipToInformation() throws InterruptedException {
        WebElement shippingInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingAddress));
        scrollToElement(shippingInfo);
        Thread.sleep(500);
        return shippingInfo.getText();
    }

    public void clickOnItemsInCartDropDown(int maxRetries){
        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                WebElement itemsInCartDropDown = wait.until(ExpectedConditions.elementToBeClickable(itemsInCartDropDownBtn));
                itemsInCartDropDown.click();
                return; // If the click is successful, exit the loop
            }catch (Exception e) {
                // Handle the exception or log it
                e.printStackTrace();
            }
        }
        // If the loop completes without a successful click, you can throw an exception or log an error.
    }


    public String getProductOnePrice(){
        WebElement priceProductOne = wait.until(ExpectedConditions.visibilityOfElementLocated(productOnePricePaymentScreen));
        return priceProductOne.getText();
    }

    public String getProductTwoPrice(){
        WebElement priceProductTwo = wait.until(ExpectedConditions.visibilityOfElementLocated(productTwoPricePaymentScreen));
        return priceProductTwo.getText();
    }


    //I have implemented a Retry Mechanism where I try to click "Place Order" button multiple times before declaring a failure.
    // I did this because the element is often not clickable due to transient issues.
    public void clickPlaceOrderWithRetry(int maxRetries) {
        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
                orderBtn.click();
                return; // If the click is successful, exit the loop
            } catch (Exception e) {
                // Handle the exception or log it
                e.printStackTrace();
            }
        }
        // If the loop completes without a successful click, you can throw an exception or log an error.
    }
}