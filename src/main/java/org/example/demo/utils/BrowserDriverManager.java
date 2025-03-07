package org.example.demo.utils;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserDriverManager {
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(BrowserDriverManager.class);


    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static WebDriver getDriver(String browser) {
        if (threadLocalDriver.get() == null) {
            setDriver(DriverFactory.createDriver(browser));
        }
        threadLocalDriver.get().manage().window().maximize();
        return threadLocalDriver.get();
    }



    public static void quitDriver() {
        if (threadLocalDriver.get() != null) {

            logger.info("Quitting driver for test in thread: {}  ",
                    Thread.currentThread().getId());

            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }
}
