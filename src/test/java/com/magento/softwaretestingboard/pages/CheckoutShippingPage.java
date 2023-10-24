package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutShippingPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By streetAddress = By.xpath("//*[@name='street[0]']");
    private final By customerCity = By.xpath("//*[@name='city']");
    private final By zipCode = By.xpath("//*[@name='postcode']");
    private final By country = By.xpath("//*[@name='country_id']");
    private final By phoneNumber = By.xpath("//*[@name='telephone']");
    private final By shippingMethod = By.xpath("//*[@checked='true']");
    private final By nextBtn = By.xpath("//*[@id='shipping-method-buttons-container']/div/button/span");
    private final By itemsInCart = By.xpath("//*[@class='title']//*[contains(text(), '2')]");
    private final By productOneName = By.xpath("(//*[@class='product-item-details'])[1]//*[@class='product-item-name']");
    private final By productOneQty = By.xpath("(//*[@class='product-item-details'])[1]//*[@class='value']");
    private final By productOnePrice = By.xpath("(//*[@class='product-item-details'])[1]//*[@class='price']");
    private final By productTwoName = By.xpath("(//*[@class='product-item-details'])[2]//*[@class='product-item-name']");
    private final By productTwoQty = By.xpath("(//*[@class='product-item-details'])[2]//*[@class='value']");
    private final By productTwoPrice = By.xpath("(//*[@class='product-item-details'])[2]//*[@class='price']");
    private final By orderSummaryLink = By.xpath("//div[@class='title']");


    public CheckoutShippingPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean checkNorthMacedoniaIsListed(String optionText) {
        WebElement selectStateFromDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(country));
        Select select = new Select(selectStateFromDropDown);
        select.selectByVisibleText(optionText);

        boolean isOptionPresent = false;

        for (WebElement option : select.getOptions()) {
            if (option.getText().equals("North Macedonia")) {
                isOptionPresent = true;
                break;
            }
        }
        return isOptionPresent;
    }

    public void enterStreetAddress(String street) {
        WebElement streetAddressField = wait.until(ExpectedConditions.visibilityOfElementLocated(streetAddress));
        streetAddressField.sendKeys(street);
    }

    public void enterCity(String city) {
        WebElement cityAddressField = wait.until(ExpectedConditions.visibilityOfElementLocated(customerCity));
        cityAddressField.sendKeys(city);
    }

    public void enterZipCode(String zip) {
        WebElement zipCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(zipCode));
        zipCodeField.sendKeys(zip);
    }

    public void selectCountry(String countryMKD) throws InterruptedException {
        WebElement selectStateFromDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(country));
        selectStateFromDropDown.sendKeys(countryMKD);
        Thread.sleep(500);
        selectStateFromDropDown.sendKeys(Keys.ENTER);
    }

    public void enterPhoneNumber(String phone) {
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumber));
        phoneField.sendKeys(phone);
    }

    public void selectFixedShippingMethod() {
        WebElement radioShippingMethod = wait.until(ExpectedConditions.elementToBeClickable(shippingMethod));
        radioShippingMethod.click();
    }

    public void clickNextButtonToThePayment() {
        WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(nextBtn));
        nextButton.click();
    }

    public String getCartSummaryItemsCount() {
        WebElement cartCounterElement = wait.until(ExpectedConditions.visibilityOfElementLocated(itemsInCart));
        return cartCounterElement.getText();
    }

    private final String expectedCheckoutURL = "https://magento.softwaretestingboard.com/checkout/#shipping";

    // Method to compare the current URL with the expected URL
    public boolean isOnCheckoutPage() {
        // Get the actual URL
        wait.until(ExpectedConditions.urlToBe(expectedCheckoutURL));
        String actualUrl = driver.getCurrentUrl();

        // Compare the actual URL with the expected URL
        return actualUrl.equals(expectedCheckoutURL);
    }

    //I have implemented a Retry Mechanism where I try to click "Place Order" button multiple times before declaring a failure.
    // I did this because the element is often not clickable due to transient issues.
    public void clickOrderSummaryLink(int maxRetries) {
        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                WebElement orderSummaryLink1 = wait.until(ExpectedConditions.visibilityOfElementLocated(orderSummaryLink));
                orderSummaryLink1.click();
                return; // If the click is successful, exit the loop
            } catch (Exception e) {
                // Handle the exception or log it
                e.printStackTrace();
            }
        }
        // If the loop completes without a successful click, you can throw an exception or log an error.
    }


    public String getProductOneName() {
        WebElement cartProdOneName = wait.until(ExpectedConditions.visibilityOfElementLocated(productOneName));
        return cartProdOneName.getText();
    }

    public String getProductOneQty() {
        WebElement cartProdOneQty = wait.until(ExpectedConditions.visibilityOfElementLocated(productOneQty));
        return cartProdOneQty.getText();
    }

    public String getProductOnePrice() {
        WebElement cartProdOnePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productOnePrice));
        return cartProdOnePrice.getText();
    }

    public String getProductTwoName() {
        WebElement cartProdTwo = wait.until(ExpectedConditions.visibilityOfElementLocated(productTwoName));
        return cartProdTwo.getText();
    }

    public String getProductTwoQty() {
        WebElement cartProdTwoQty = wait.until(ExpectedConditions.visibilityOfElementLocated(productTwoQty));
        return cartProdTwoQty.getText();
    }

    public String getProductTwoPrice() {
        WebElement cartProdTwoPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productTwoPrice));
        return cartProdTwoPrice.getText();
    }
}