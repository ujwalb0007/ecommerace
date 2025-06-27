package com.example.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class LoginPage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private WebDriver driver;
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(usernameField));
        logger.info("Login page loaded");
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
        logger.info("Entered username: {}", username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        logger.info("Entered password");
    }

    public InventoryPage clickLoginButton() {
        driver.findElement(loginButton).click();
        logger.info("Clicked login button");
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}