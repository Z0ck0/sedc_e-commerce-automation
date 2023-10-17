package com.magento.softwaretestingboard.tests;

import com.magento.softwaretestingboard.pages.*;
import com.magento.softwaretestingboard.utilities.BrowserManager;
import com.magento.softwaretestingboard.utilities.RandomCredentialsGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    BrowserManager h1_browserManager;
    private String browserName = "chrome";


    //Page Objects
    Home p1_home;
    CreateAccount p2_createAccount;
    MyAccount p3_myAccount;
    Login p4_login;
    MenJackets p5_menJackets;
    CheckoutShipping p6_checkoutShipping;
    CheckoutPayments p7_checkoutPayments;
    CheckoutSuccess p8_checkoutSuccess;


    // Variables to store generated data
    public String getRandomEmail;
    public String getRandomPassword;
    public String getRandomFirstName;
    public String getRandomLastName;


    public BaseTest() {
        // Generate a random data once for the entire test class
        getRandomEmail = RandomCredentialsGenerator.getRandomEmail();
        getRandomPassword = RandomCredentialsGenerator.getRandomPassword();
        getRandomFirstName = RandomCredentialsGenerator.generateRandomFirstName(5);
        getRandomLastName = RandomCredentialsGenerator.generateRandomLastName(7);
    }


    @BeforeMethod
    public void setUp() {
        // Initialize the browser driver manager
        h1_browserManager = new BrowserManager();

        // Initiate the WebDriver, WebDriverWait and Actions
        driver = h1_browserManager.initiateDriver(browserName);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);

        // Initialize Page Objects
        p1_home = new Home(driver, wait);
        p2_createAccount = new CreateAccount(driver, wait);
        p3_myAccount = new MyAccount(driver, wait);
        p4_login = new Login(driver, wait);
        p5_menJackets = new MenJackets(driver, wait, actions);
        p6_checkoutShipping = new CheckoutShipping(driver, wait);
        p7_checkoutPayments = new CheckoutPayments(driver, wait);
        p8_checkoutSuccess = new CheckoutSuccess(driver, wait);

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}