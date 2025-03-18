package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.example.demo.utils.Waiter.waitForElementToBeClickable;
import static org.example.demo.utils.Waiter.waitForElementToBePresent;

public abstract class BasePage {
    protected BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    protected void scrollAndClick(By locator) {
        WebElement element = waitForElementToBePresent(locator);
        scrollAndClick(element);
    }

    protected void scrollAndClick(WebElement element) {
        scrollToElement(element);
        clickElementWithJS(waitForElementToBeClickable(element));
    }

    protected void scrollToElement(WebElement element) {
        if (element.isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].focus();", element);
        }
    }

    private void clickElementWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
    }
}
