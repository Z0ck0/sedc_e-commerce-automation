package com.magento.softwaretestingboard.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetup {
    private WebDriver driver;
    private ChromeOptions options;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;



    public WebDriver initiateDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-popup-blocking", "--incognito");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--start-maximized", "--disable-popup-blocking");
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equalsIgnoreCase("edge")) {
            edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--start-maximized", "--disable-popup-blocking", "--incognito");
            driver = new EdgeDriver(edgeOptions);
        } else {
            System.out.println("Invalid browser name provided");
        }
        return driver;
    }
}