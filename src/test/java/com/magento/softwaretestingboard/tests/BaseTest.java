package com.magento.softwaretestingboard.tests;

import com.magento.softwaretestingboard.pages.*;
import com.magento.softwaretestingboard.utilities.CredentialsGenerator;
import com.magento.softwaretestingboard.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    // Declaring WebDriver, WebDriverWait, and Actions for browser and user interaction
    protected WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;


    DriverSetup driverSetup;
    private final String browserName = "chrome";


    //Page Objects representing various pages in the application.
    BasePage basePage;
    HomePage homePage;
    CreateAccountPage createAccountPage;
    MyAccountPage myAccountPage;
    LoginPage loginPage;
    MenJacketsPage menJacketsPage;
    CheckoutShippingPage checkoutShippingPage;
    CheckoutPaymentsPage checkoutPaymentsPage;
    CheckoutSuccessPage checkoutSuccessPage;



    // Variables to store generated data for testing.
    public String getRandomEmail;
    public String getRandomPassword;
    public String getRandomFirstName;
    public String getRandomLastName;


    public BaseTest() {
        // Generate random data once for the entire test class.
        getRandomEmail = CredentialsGenerator.getRandomEmail();
        getRandomPassword = CredentialsGenerator.getRandomPassword();
        getRandomFirstName = CredentialsGenerator.generateRandomFirstName(5);
        getRandomLastName = CredentialsGenerator.generateRandomLastName(7);
    }



    @BeforeMethod
    public void setUp() {
        driverSetup = new DriverSetup();
        // Initiate the WebDriver, WebDriverWait and Actions
        driver = driverSetup.initiateDriver(browserName);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);

        // Initialize Page Objects
        basePage = new BasePage(driver, wait, actions);
        homePage = new HomePage(driver, wait);
        createAccountPage = new CreateAccountPage(driver, wait);
        myAccountPage = new MyAccountPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        menJacketsPage = new MenJacketsPage(driver, wait, actions);
        checkoutShippingPage = new CheckoutShippingPage(driver, wait);
        checkoutPaymentsPage = new CheckoutPaymentsPage(driver, wait);
        checkoutSuccessPage = new CheckoutSuccessPage(driver, wait);

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}