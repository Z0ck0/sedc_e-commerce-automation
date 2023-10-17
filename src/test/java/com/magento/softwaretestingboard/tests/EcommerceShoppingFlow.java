package com.magento.softwaretestingboard.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommerceShoppingFlow extends BaseTest {

    //Test 1: Register an Account
    @Test(priority = 1)
    public void registerAccount() {
        p1_home.navigateToHomePage();
        // Assert that the user is on 'Home' page
        Assert.assertTrue(p1_home.isOnHomePage(),
                "The user is not on the 'Home' page.");

        p1_home.clickCreateAccount();

        // Generate unique First and Last Names for each test run
        p2_createAccount.enterFirstName(getRandomFirstName);
        p2_createAccount.enterLastName(getRandomLastName);

        // Generate unique email and password for each test run
        p2_createAccount.enterEmail(getRandomEmail);
        p2_createAccount.enterPassword(getRandomPassword);
        p2_createAccount.enterConfirmPassword(getRandomPassword);

        p2_createAccount.clickCreateAccountButton();

        // Assert successful registration and redirection to the 'My Account' page.
        Assert.assertEquals(p3_myAccount.getSuccessMessage(), "Thank you for registering with Main Website Store.",
                "Registration was not successful. Expected success message is missing.");
        Assert.assertTrue(p3_myAccount.isOnMyAccountPage(),
                "After successful registration, the user was not redirected to the 'My Account' page.");

        // Assert the presence and accuracy of user 'Account Information'
        // Using '.replaceAll("\\s+", " ").trim()' to remove line break between the last name and email, and ensure a consistent format for comparison.
        Assert.assertEquals(p3_myAccount.getAccountContactInformation().replaceAll("\\s+", " ").trim(),
                getRandomFirstName + " " + getRandomLastName + " " + getRandomEmail,
                "The 'Account Information' displayed does not match the expected user information.");

        // Assert the presence of the "Sign Out" link on the Customer Menu.
        Assert.assertEquals(p3_myAccount.isSignOutLinkVisible(), "Sign Out",
                "The 'Sign Out' link is NOT present on the Customer Menu.");
    }

    // Test 2: Add 2 Products to the Shopping Cart and Proceed to Checkout
    @Test(priority = 2, dependsOnMethods = "registerAccount")
    public void addProductsToCart() {
        p1_home.navigateToHomePage();
        p1_home.clickSignIn();

        // Utilizes the email and password generated during the registration process.
        p4_login.enterRegisteredEmail(getRandomEmail);
        p4_login.enterRegisteredPassword(getRandomPassword);
        p4_login.clickSignInButton();
        p1_home.hoverToMenMenu();
        p1_home.hoverToTops();
        p1_home.clickOnJackets();

        p5_menJackets.addToCartBlackXLFitnessJackshirt();
        // Assert the addition of 'Proteus Fitness JackShirt' product to the shopping cart.
        Assert.assertEquals(p5_menJackets.getSuccessMessageFitnessJackshirt(), "You added Proteus Fitness Jackshirt to your shopping cart.",
                "'Proteus Fitness JackShirt' product was not added to the shopping cart.");

        p5_menJackets.addToCartGreenXLWindJackshirt();
        // Assert the addition of 'Montana Wind Jacket' product to the shopping cart.
        Assert.assertEquals(p5_menJackets.getSuccessMessageWindJacket(), "You added Montana Wind Jacket to your shopping cart.",
                "'Montana Wind Jacket' product was not added to the shopping cart.");

        p5_menJackets.clickProceedToCheckout();
        // Assert that the user is redirected to 'Checkout' page.
        Assert.assertTrue(p6_checkoutShipping.isOnCheckoutPage(),
                "User is NOT redirected to the 'Checkout' page");

        // Assert that the Order Summary on the Checkout page correctly displays the number of items added.
        Assert.assertEquals(p6_checkoutShipping.getCartSummaryItemsCount(), "2",
                "Order Summary on the Checkout page DID NOT correctly display the number of added items.");

        p6_checkoutShipping.clickOrderSummaryLink(3);

        // Asserting that the correct products, quantities, and prices are added to the cart.
        Assert.assertEquals(p6_checkoutShipping.getProductOneName(), "Proteus Fitness Jackshirt",
                "Product name mismatch for the first cart item.");
        Assert.assertEquals(p6_checkoutShipping.getProductOneQty(), "1",
                "Quantity mismatch for the first cart item.");
        Assert.assertEquals(p6_checkoutShipping.getProductOnePrice(), "$45.00",
                "Price mismatch for the first item.");
        Assert.assertEquals(p6_checkoutShipping.getProductTwoName(), "Montana Wind Jacket",
                "Product name mismatch for the second cart item.");
        Assert.assertEquals(p6_checkoutShipping.getProductTwoQty(), "1",
                "Quantity mismatch for the second cart item.");
        Assert.assertEquals(p6_checkoutShipping.getProductTwoPrice(), "$49.00",
                "Price mismatch for the second cart item.");
    }

    // Test 3: End-to-End Test of the Entire Flow
    @Test(priority = 3, dependsOnMethods = "addProductsToCart")
    public void endToEnd() throws InterruptedException {
        p1_home.navigateToHomePage();
        p1_home.clickSignIn();


        //Assert User is navigated to 'Customer Login' Page.
        Assert.assertTrue(p4_login.isOnLoginPage(),
                "User is not on the 'Customer Login' Page.");

        // Utilizes the email and password generated during the registration process.
        p4_login.enterRegisteredEmail(getRandomEmail);
        p4_login.enterRegisteredPassword(getRandomPassword);
        p4_login.clickSignInButton();

        // Assert that the user is redirected to 'Home' page after successful Login.
        Assert.assertTrue(p3_myAccount.isOnHomePage(),
                "The user was not redirected to the 'Home' page after successful login.");

        // Assert Welcome message and the customer Full Name on User Account Menu Element.
        Assert.assertEquals(p1_home.checkWelcomeMessageOnUserAccountMenu(), "Welcome, " + getRandomFirstName + " " + getRandomLastName + "!",
                "The welcome message and customer name on the User Account Menu do not match the expected values.");

        //Assert that 'Main Store Navigation Menu' is displayed on 'My Account' page.
        Assert.assertTrue(p3_myAccount.mainStoreMenuIsDisplayed(),
                "The 'Main Store Navigation Menu' is not displayed on the 'My Account' page.");

        p1_home.hoverToMenMenu();
        p1_home.hoverToTops();
        p1_home.clickOnJackets();

        p5_menJackets.addToCartBlackXLFitnessJackshirt();
        // Assert the addition of 'Proteus Fitness JackShirt' product to the shopping cart.
        Assert.assertEquals(p5_menJackets.getSuccessMessageFitnessJackshirt(), "You added Proteus Fitness Jackshirt to your shopping cart.",
                "The product 'Proteus Fitness JackShirt' was not successfully added to the shopping cart.");


        p5_menJackets.addToCartGreenXLWindJackshirt();
        // Assert the addition of 'Montana Wind Jacket' product to the shopping cart.
        Assert.assertEquals(p5_menJackets.getSuccessMessageWindJacket(), "You added Montana Wind Jacket to your shopping cart.",
                "The product 'Montana Wind Jacket' was not successfully added to the shopping cart.");


        p5_menJackets.clickProceedToCheckout();
        // Assert successful navigation to the 'Checkout' page.
        Assert.assertTrue(p6_checkoutShipping.isOnCheckoutPage(),
                "The user was not redirected to the expected 'Checkout' page.");


        // Assert that 'North Macedonia' is available country in Country Drop Down menu.
        Assert.assertTrue(p6_checkoutShipping.checkNorthMacedoniaIsListed("North Macedonia"),
                "'North Macedonia' is not available");

        p6_checkoutShipping.selectCountry("North Macedonia");
        p6_checkoutShipping.enterStreetAddress("3 MUB");
        p6_checkoutShipping.enterCity("Skopje");
        p6_checkoutShipping.enterZipCode("1000");
        p6_checkoutShipping.enterPhoneNumber("+38977334455");
        p6_checkoutShipping.selectFixedShippingMethod();
        p6_checkoutShipping.clickNextButtonToThePayment();

        // Assert successful navigation to the 'Payment' page.
        Assert.assertTrue(p7_checkoutPayments.isOnPaymentPage(),
                "The navigation to the 'Payment' page was not successful.");

        // Assert Cart item count.
        Assert.assertEquals(p7_checkoutPayments.getTotalItemsInCart(), "4",
                "The total number of items in the cart does not match the expected count.");

        // Assert Cart Subtotal price, Shipping Price and Order Total Price.
        Assert.assertEquals(p7_checkoutPayments.getCartSubtotalPrice(), "$188.00",
                "The 'Cart Subtotal' price does not match expected value");
        Assert.assertEquals(p7_checkoutPayments.getShippingPrice(), "$20.00",
                "The 'Shipping' price does not match expected value");
        Assert.assertEquals(p7_checkoutPayments.getOrderTotalPrice(), "$208.00",
                "The 'Order Total' price does not match expected value");

        // Assert shipping information.
        Assert.assertEquals(p7_checkoutPayments.getShipToInformation().replaceAll("\\s+", " ").trim(),
                getRandomFirstName + " " + getRandomLastName + " 3 MUB Skopje, 1000 North Macedonia +38977334455",
                "Shipping information does not match the expected value.");

        p7_checkoutPayments.clickOnItemsInCartDropDown(3);
        // Assert the Order summary products prices.
        Assert.assertEquals(p7_checkoutPayments.getProductOnePrice(), "$90.00",
                "The price for the first item in the order summary is incorrect.");
        Assert.assertEquals(p7_checkoutPayments.getProductTwoPrice(), "$98.00",
                "The price for the second item in the order summary is incorrect.");

        p7_checkoutPayments.clickPlaceOrderWithRetry(3);

        // Assert successful navigation to the 'Success' page.
        Assert.assertTrue(p8_checkoutSuccess.isOnSuccessPage(),
                "The navigation to the 'Success' page was NOT successful.");

        // Assert successful purchase!
        Assert.assertEquals(p8_checkoutSuccess.getSuccessfulPurchaseMessage(), "Thank you for your purchase!",
                "The successful purchase message does NOT match the expected message.");

        //Assert 'Print receipt' link is present on the Order Success Page.
        Assert.assertEquals(p8_checkoutSuccess.confirmPrintReceiptLinkPresent(), "Print receipt",
                "'Print receipt' link is NOT present on the Order Success Page.");

        //Assert 'Continue Shopping' button is present on the Order Success Page
        Assert.assertEquals(p8_checkoutSuccess.confirmContinueButtonPresent(), "Continue Shopping",
                "'Continue Shopping' button is NOT present on Order Success Page");
    }
}