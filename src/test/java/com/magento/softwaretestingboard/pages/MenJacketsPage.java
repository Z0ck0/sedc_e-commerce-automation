package com.magento.softwaretestingboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MenJacketsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By proteusFitnessJackshirtProductBlock = By.xpath("//*[@class='product-item-info']//*[contains(text(), 'Proteus Fitness Jackshirt')]");
    private By proteusFitnessJackshirtSizeOptionXL = By.xpath("(//*[@id='option-label-size-143-item-170'])[1]");
    private By proteusFitnessJackshirtColorOptionBlack = By.xpath("(//*[@id='option-label-color-93-item-49'])[1]");
    private By proteusFitnessJackshirtAddToCartButton = By.xpath("(//span[contains(text(),'Add to Cart')])[1]");

    private By montanaWindJacketProductBlock = By.xpath("//*[@class='product-item-info']//*[contains(text(), 'Montana Wind Jacket')]");
    private By montanaWindJacketSizeOptionXL = By.xpath("//*[@class='swatch-opt-414']//*[@id='option-label-size-143-item-170']");
    private By montanaWindJacketColorOptionGreen = By.xpath("(//*[@id='option-label-color-93-item-53'])[1]");
    private By montanaWindJacketAddToCartButton = By.xpath("(//span[contains(text(),'Add to Cart')])[2]");
    private By cartIcon = By.xpath("//*[@class='action showcart']//*[@class='counter qty']");
    private By fitnessJackShirtSuccessMessage = By.xpath("//*[contains(text(), 'You added Proteus Fitness Jackshirt to your')]");
    private By windJacketSuccessMessage = By.xpath("//*[contains(text(), 'You added Montana Wind Jacket to your')]");
    private By proceedToCheckoutButton = By.xpath("//button[@title='Proceed to Checkout']");



    public MenJacketsPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
    }



    //     Helper method to scroll to an element using JavaScript
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void addToCartBlackXLFitnessJackshirt() {
        try {
            WebElement fitnessJackshirt = wait.until(ExpectedConditions.visibilityOfElementLocated(proteusFitnessJackshirtProductBlock));
            actions.moveToElement(fitnessJackshirt).perform();

            // Scroll to the Product Size element (jackshirt_XL_Size) to ensure it's in view
            WebElement size = wait.until(ExpectedConditions.elementToBeClickable(proteusFitnessJackshirtSizeOptionXL));
            scrollToElement(size);
            size.click();

            // Scroll to the Product Color element (jackshirt_Black_Color) to ensure it's in view
            WebElement color = wait.until(ExpectedConditions.elementToBeClickable(proteusFitnessJackshirtColorOptionBlack));
            scrollToElement(color);
            color.click();

            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(proteusFitnessJackshirtAddToCartButton));
            addButton.click();

            // Scroll to the top of the page using JavaScript
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
            Thread.sleep(500);

        } catch (Exception e) {

        }
    }


    public void addToCartGreenXLWindJackshirt() {
        try {
            WebElement windJackshirt = wait.until(ExpectedConditions.visibilityOfElementLocated(montanaWindJacketProductBlock));
            actions.moveToElement(windJackshirt).perform();

            WebElement windSize = wait.until(ExpectedConditions.elementToBeClickable(montanaWindJacketSizeOptionXL));
            scrollToElement(windSize);
            windSize.click();

            WebElement windColor = wait.until(ExpectedConditions.elementToBeClickable(montanaWindJacketColorOptionGreen));
            scrollToElement(windColor);
            windColor.click();

            WebElement windAddButton = wait.until(ExpectedConditions.elementToBeClickable(montanaWindJacketAddToCartButton));
            windAddButton.click();
            Thread.sleep(500);

            // Use JavaScript to scroll to the top of the page
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
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