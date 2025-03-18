package org.example.demo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waiter {

    private static final Duration TIMEOUT = Duration.ofSeconds(20);
    private static final Duration POLLING_INTERVAL = Duration.ofMillis(500);

    private Waiter() {
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
    }

    public static WebElement waitForElementToBeVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementToBeVisible(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementToBeClickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForElementToBePresent(By locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static List<WebElement> waitForAllElementsToBePresent(By locator) {
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void waitForAllElementsToBeInvisible(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(DriverManager.getDriver()).withTimeout(TIMEOUT).pollingEvery(POLLING_INTERVAL);
    }
}
