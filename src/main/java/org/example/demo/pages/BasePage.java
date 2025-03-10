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
import java.util.List;

public abstract class BasePage {
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    protected BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToBeVisible(WebElement element) {
        return DriverManager.getWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        return DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForElementToBePresent(By locator) {
        return DriverManager.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> waitForAllElementsToBeVisible(By locator) {
        return DriverManager.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void scrollToElement(By locator) {
        WebElement element = waitForElementToBePresent(locator);
        if (element.isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].focus();", element);
        }
    }

    protected void scrollToElement(WebElement element) {
        if (element.isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].focus();", element);
        }
    }

    protected void scrollAndClick(By locator) {
        scrollToElement(locator);
        clickElementWithJS(waitForElementToBeClickable(locator));
    }

    protected void scrollAndClick(WebElement element) {
        scrollToElement(element);
        clickElementWithJS(waitForElementToBeClickable(element));
    }

    private void clickElementWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
    }
}
