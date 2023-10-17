package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MenJackets {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By fitnessProductImage = By.xpath("//*[@class='product-item-info']//*[contains(text(), 'Proteus Fitness Jackshirt')]");
    //*[@id="maincontent"]/div[3]/div[1]/div[3]/ol/li[1]/div
    //*[@class="product-item-info"]//*[contains(text(), 'Proteus Fitness Jackshirt')]
    //*[contains(text(), 'Proteus Fitness Jackshirt')]
    //*[@class='item product product-item'][1]
    private By jackshirt_XL_Size = By.xpath("(//*[@id='option-label-size-143-item-170'])[1]");
    private By jackshirt_Black_Color = By.xpath("(//*[@id='option-label-color-93-item-49'])[1]");
    private By addToCartFitnessButton = By.xpath("(//span[contains(text(),'Add to Cart')])[1]");


    private By windProductBlock = By.xpath("//*[@class='product-item-info']//*[contains(text(), 'Montana Wind Jacket')]");
    //*[@id="maincontent"]/div[3]/div[1]/div[3]/ol/li[2]/div
    //*[@class='product-item-info']//*[contains(text(), 'Montana Wind Jacket')]
    // *[contains(text(), 'Montana Wind Jacket')]
    //*[@class='item product product-item'][2]
    private By wind_XL_Size = By.xpath("//*[@class='swatch-opt-414']//*[@id='option-label-size-143-item-170']");
    private By wind_Green_Color = By.xpath("(//*[@id='option-label-color-93-item-53'])[1]");
    private By addToCartWindButton = By.xpath("(//span[contains(text(),'Add to Cart')])[2]");

    private By cartIcon = By.xpath("//*[@class='action showcart']//*[@class='counter qty']");

    private By fitnessJackShirtSuccessMessage = By.xpath("//*[contains(text(), 'You added Proteus Fitness Jackshirt to your')]");

    private By windJacketSuccessMessage = By.xpath("//*[contains(text(), 'You added Montana Wind Jacket to your')]");

    private By proceedToCheckoutButton = By.xpath("//button[@title='Proceed to Checkout']");


    public MenJackets(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }

    // Helper method to scroll to an element using JavaScript
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void addToCartBlackXLFitnessJackshirt() {
        try {
            WebElement fitnessJackshirt = wait.until(ExpectedConditions.visibilityOfElementLocated(fitnessProductImage));
            // Actions actions = new Actions(driver);
            actions.moveToElement(fitnessJackshirt).perform();

            // Scroll to the Product Size element (jackshirt_XL_Size) to ensure it's in view
            WebElement size = wait.until(ExpectedConditions.elementToBeClickable(jackshirt_XL_Size));
            scrollToElement(size);
            size.click();

            // Scroll to the Product Color element (jackshirt_Black_Color) to ensure it's in view
            WebElement color = wait.until(ExpectedConditions.elementToBeClickable(jackshirt_Black_Color));
            scrollToElement(color);
            color.click();

            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartFitnessButton));
            addButton.click();
            Thread.sleep(500);

        } catch (Exception e) {

        }
    }


    public void addToCartGreenXLWindJackshirt() {
        try {
            WebElement windJackshirt = wait.until(ExpectedConditions.visibilityOfElementLocated(windProductBlock));
            // Actions actions = new Actions(driver);
            actions.moveToElement(windJackshirt).perform();

            WebElement windSize = wait.until(ExpectedConditions.elementToBeClickable(wind_XL_Size));
            scrollToElement(windSize);
            windSize.click();

            WebElement windColor = wait.until(ExpectedConditions.elementToBeClickable(wind_Green_Color));
            scrollToElement(windColor);
            windColor.click();

            WebElement windAddButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartWindButton));
            windAddButton.click();
            Thread.sleep(500);
        } catch (Exception e) {

        }
    }


    public void clickProceedToCheckout() {
        // Scroll to the top of the page using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

        WebElement CartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
        CartIcon.click();
        WebElement proceedToCheckoutBtn = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutBtn.click();
    }

    public String getSuccessMessageFitnessJackshirt() {
        WebElement successJackshir = wait.until(ExpectedConditions.visibilityOfElementLocated(fitnessJackShirtSuccessMessage));
        scrollToElement(successJackshir);
        return successJackshir.getText();
    }

    public String getSuccessMessageWindJacket() {
        WebElement successWindJacket = wait.until(ExpectedConditions.visibilityOfElementLocated(windJacketSuccessMessage));
        scrollToElement(successWindJacket);
        return successWindJacket.getText();
    }

}