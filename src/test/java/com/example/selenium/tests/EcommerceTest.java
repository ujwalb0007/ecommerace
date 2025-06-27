package com.example.selenium.tests;

import com.example.selenium.config.ConfigReader;
import com.example.selenium.pages.CartPage;
import com.example.selenium.pages.InventoryPage;
import com.example.selenium.pages.LoginPage;
import com.example.selenium.utils.ReportGenerator;
import com.example.selenium.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class EcommerceTest {
    private static final Logger logger = LoggerFactory.getLogger(EcommerceTest.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeClass
    public void setUp() {
        driver = WebDriverUtil.getChromeDriver();
        driver.get(ConfigReader.getBaseUrl());
        loginPage = new LoginPage(driver);
        logger.info("Test setup completed");
    }

    @Test(priority = 1)
    public void testLogin() {
        loginPage.enterUsername(ConfigReader.getUsername());
        loginPage.enterPassword(ConfigReader.getPassword());
        inventoryPage = loginPage.clickLoginButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed");
        logger.info("Login test passed");
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testExtractProductsAndAddToCart() {
        List<InventoryPage.Product> products = inventoryPage.getProducts();
        Assert.assertFalse(products.isEmpty(), "No products found");
        inventoryPage.addFirstProductToCart();
        ReportGenerator.generateProductReport(products, ConfigReader.getReportPath());
        logger.info("Extracted {} products and generated report", products.size());
    }

    @Test(priority = 3, dependsOnMethods = "testExtractProductsAndAddToCart")
    public void testCartContents() {
        cartPage = inventoryPage.goToCart();
        List<String> cartItems = cartPage.getCartItemNames();
        Assert.assertFalse(cartItems.isEmpty(), "Cart is empty");
        logger.info("Cart contains {} items", cartItems.size());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed");
        }
    }
}