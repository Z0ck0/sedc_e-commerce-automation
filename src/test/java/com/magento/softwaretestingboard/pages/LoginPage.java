package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage{

    private WebDriver driver;
    private WebDriverWait wait;


    private By emailLoginPageField = By.xpath("//*[@id='email']");
    private By passwordLoginPageField = By.xpath("(//*[@id='pass'])[1]");
    private By signInButtonLoginPage = By.xpath("(//*[@id='send2']/span)[1]");
    private By signInButtonLoginPage1 = By.xpath("(//*[@id='send2']/span)[1]");



    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void enterRegisteredEmail(String enterEmail) {

        WebElement newAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(emailLoginPageField));
        newAccount.sendKeys(enterEmail);
    }

    public void enterRegisteredPassword(String enterPassword) {
        WebElement newAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoginPageField));
        newAccount.sendKeys(enterPassword);
    }

    public void clickSignInButton() {
        WebElement newAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(signInButtonLoginPage));
        newAccount.click();
    }

    private final String expectedCustomerLoginPage = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";

    // Method to compare the current URL with the expected URL
    public boolean isOnLoginPage() {
//        return isOnPage(expectedCustomerLoginPage);

        // Get the actual URL
        wait.until(ExpectedConditions.urlToBe(expectedCustomerLoginPage));
        String actualUrl = driver.getCurrentUrl();

        // Compare the actual URL with the expected URL
        return actualUrl.equals(expectedCustomerLoginPage);
    }
}