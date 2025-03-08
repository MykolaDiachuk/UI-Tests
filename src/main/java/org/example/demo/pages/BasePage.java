package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
    public WebElement waitForElementToBeVisible(By locator) {
        return DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return DriverManager.getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBePresent(By locator) {
        return DriverManager.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void clickIfNotSelected(By locator) {
        scrollToElement(locator);
        WebElement checkbox = waitForElementToBeVisible(locator);

        if (!checkbox.isSelected()&& checkbox.isDisplayed() && checkbox.isEnabled()) {
            waitForElementToBeVisible(checkbox);
            waitForElementToBeClickable(checkbox);
            checkbox.click();
        }else {
            logger.error("Something wrong with checkbox");
        }
    }

    public void scrollToElement(By locator) {
        WebElement element = waitForElementToBePresent(locator);
        if (element.isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].focus();", element);
        }
    }
    public void scrollAndClick(By locator) {
        scrollToElement(locator);
        waitForElementToBeClickable(locator).click();
    }

    public WebElement fluentWait(By locator) {
        return DriverManager.getFluentWait().until(driver -> driver.findElement(locator));
    }


}
