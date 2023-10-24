package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage{

    private  WebDriver driver;
    private WebDriverWait wait;

    private By firstNameField = By.xpath("//*[@id='firstname']");
    private By lastNameField = By.xpath("//*[@id='lastname']");
    private By customerEmailField = By.xpath("//*[@id='email_address']");
    private By customerPasswordField = By.xpath("//*[@id='password']");
    private By confirmPasswordField = By.xpath("//*[@id='password-confirmation']");
    private By createAccountButton = By.xpath("//*[@title='Create an Account']");

    public CreateAccountPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void enterFirstName(String nameF) {
        WebElement fName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        fName.clear();
        fName.sendKeys(nameF);
    }

    public void enterLastName(String nameL) {
        WebElement lName = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        lName.clear();
        lName.sendKeys(nameL);
    }

    public void enterEmail(String email) {
        WebElement cEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(customerEmailField));
        cEmail.clear();
        cEmail.sendKeys(email);
    }

    public void enterPassword(String password1) {
        WebElement cPass = wait.until(ExpectedConditions.visibilityOfElementLocated(customerPasswordField));
        cPass.clear();
        cPass.sendKeys(password1);
    }

    public void enterConfirmPassword(String password2) {
        WebElement cPass2 = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        cPass2.clear();
        cPass2.sendKeys(password2);
    }

    public void clickCreateAccountButton() {
        WebElement clickCreate = wait.until(ExpectedConditions.visibilityOfElementLocated(createAccountButton));
        clickCreate.click();
    }
}