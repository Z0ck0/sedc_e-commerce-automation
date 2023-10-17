package com.magento.softwaretestingboard.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    private final WebDriver driver;
    private final WebDriverWait wait;

    //    private final By createAccountLink = By.xpath("(//*[contains(text(), 'Create an Account')])[1]");
    //    private final By signInLink = By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]");
    @FindBy(xpath = "(//*[contains(text(), 'Create an Account')])[1]")
    WebElement createAccountLink;
    @FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
    WebElement signInLink;
    private final By menNavigationMenuLink = By.xpath("//*[@id='ui-id-5']/span[2]");
    private final By topsMensCategory = By.xpath("//*[@id='ui-id-17']/span[2]");
    private final By jacketsMenProducts = By.xpath("//*[@id='ui-id-19']");
    private final By userAccountMenuElement = By.xpath("(//span[@class='logged-in'])[1]");
    private final String expectedCustomerHomePage = "https://magento.softwaretestingboard.com/";


    public Home(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);

    }


    public void navigateToHomePage() {
        driver.navigate().to("https://magento.softwaretestingboard.com/");
    }


    public void clickCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountLink)).click();
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
    }


    public void hoverToMenMenu() {
        WebElement menMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(menNavigationMenuLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(menMenu).perform();
    }

    public void hoverToTops() {
        WebElement topsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(topsMensCategory));
        Actions actions = new Actions(driver);
        actions.moveToElement(topsMenu).perform();
    }

    public void clickOnJackets() {
        WebElement newAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(jacketsMenProducts));
        newAccount.click();
    }

    public String checkWelcomeMessageOnUserAccountMenu() throws InterruptedException {
        WebElement userAccountMenu = wait.until(ExpectedConditions.elementToBeClickable(userAccountMenuElement));
        Thread.sleep(500);
        return userAccountMenu.getText();
    }

    public boolean isOnHomePage() {
        // Get the actual URL
        wait.until(ExpectedConditions.urlToBe(expectedCustomerHomePage));
        String actualUrl = driver.getCurrentUrl();

        // Compare the actual URL with the expected URL
        return actualUrl.equals(expectedCustomerHomePage);
    }


}
