package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);
    private WebDriver driver;
    private By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(cartItems));
        logger.info("Cart page loaded");
    }

    public List<String> getCartItemNames() {
        List<String> itemNames = new ArrayList<>();
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            itemNames.add(name);
            logger.debug("Found cart item: {}", name);
        }
        return itemNames;
    }
}