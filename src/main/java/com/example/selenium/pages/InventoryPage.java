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

public class InventoryPage {
    private static final Logger logger = LoggerFactory.getLogger(InventoryPage.class);
    private WebDriver driver;
    private By inventoryItems = By.className("inventory_item");
    private By addToCartButtons = By.cssSelector("button.btn_inventory");
    private By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(inventoryItems));
        logger.info("Inventory page loaded");
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        List<WebElement> items = driver.findElements(inventoryItems);
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            String description = item.findElement(By.className("inventory_item_desc")).getText();
            String price = item.findElement(By.className("inventory_item_price")).getText();
            products.add(new Product(name, description, price));
            logger.debug("Extracted product: {}", name);
        }
        return products;
    }

    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
        logger.info("Added first product to cart");
    }

    public CartPage goToCart() {
        driver.findElement(cartLink).click();
        logger.info("Navigated to cart page");
        return new CartPage(driver);
    }

    public static class Product {
        private String name;
        private String description;
        private String price;

        public Product(String name, String description, String price) {
            this.name = name;
            this.description = description;
            this.price = price;
        }

        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getPrice() { return price; }
    }
}