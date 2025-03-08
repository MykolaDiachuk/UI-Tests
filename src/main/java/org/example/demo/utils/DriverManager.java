package org.example.demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> threadLocalWait = new ThreadLocal<>();
    private static final ThreadLocal<FluentWait<WebDriver>> threadLocalFluentWait = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
        threadLocalWait.set(new WebDriverWait(driver, Duration.ofSeconds(10)));
        threadLocalFluentWait.set(new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500)));
        driver.manage().window().maximize();
    }

    public static void initDriver(String browser) {
        if (threadLocalDriver.get() == null) {
            setDriver(DriverFactory.createDriver(browser));
        }
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static WebDriverWait getWait() {
        return threadLocalWait.get();
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return threadLocalFluentWait.get();
    }

    public static void quitDriver() {
        if (threadLocalDriver.get() != null) {
            threadLocalWait.remove();
            threadLocalFluentWait.remove();
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }
}
