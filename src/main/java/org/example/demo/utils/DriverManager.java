package org.example.demo.utils;

import org.openqa.selenium.WebDriver;


public class DriverManager {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            setDriver(DriverFactory.createDriver(ConfigReader.getProperty("browser")));
        }
        return threadLocalDriver.get();
    }

    private static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static void quitDriver() {
        if (threadLocalDriver.get() != null) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }
}
