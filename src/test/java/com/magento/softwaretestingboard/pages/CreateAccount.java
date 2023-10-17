package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccount {

    private  WebDriver driver;
    private WebDriverWait wait;

    private By firstNameField = By.xpath("//*[@id='firstname']");
    private By lastNameField = By.xpath("//*[@id='lastname']");
    private By customerEmailField = By.xpath("//*[@id='email_address']");
    private By customerPasswordField = By.xpath("//*[@id='password']");
    private By confirmPasswordField = By.xpath("//*[@id='password-confirmation']");
    private By createAccountButton = By.xpath("//*[@title='Create an Account']");


    public CreateAccount(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterFirstName(String nameF) {
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        firstName.sendKeys(nameF);
    }

    public void enterLastName(String nameL) {
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        lastName.sendKeys(nameL);
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(customerEmailField));
        emailField.sendKeys(email);
    }

    public void enterPassword(String password1) {
        WebElement password0 = wait.until(ExpectedConditions.visibilityOfElementLocated(customerPasswordField));
        password0.sendKeys(password1);
    }

    public void enterConfirmPassword(String password2) {
        WebElement passwordConfirm = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        passwordConfirm.sendKeys(password2);
    }

    public void clickCreateAccountButton() {
        WebElement createBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(createAccountButton));
        createBtn.click();
    }
}