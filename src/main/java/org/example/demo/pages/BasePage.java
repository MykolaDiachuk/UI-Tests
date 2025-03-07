package org.example.demo.pages;

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

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected FluentWait<WebDriver> fluentWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        if (wait == null || fluentWait == null) {
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            this.fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);
        }

        PageFactory.initElements(driver, this);
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBePresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public void clickIfNotSelected(WebElement checkbox) {
        scrollToElement(checkbox);
        waitForElementToBeVisible(checkbox);
        waitForElementToBeClickable(checkbox);
        if (!checkbox.isSelected()) {
            Actions actions = new Actions(driver);
            actions.click(checkbox).build().perform();
        }
    }

    public void scrollAndClick(WebElement element) {
        scrollToElement(element);
        waitForElementToBeClickable(element).click();
    }

    public void scrollToElement(WebElement element) {
        if (element.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        }
    }

    private void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }

    public WebElement fluentWait(By locator) {
        return fluentWait.until(driver -> driver.findElement(locator));
    }


}
