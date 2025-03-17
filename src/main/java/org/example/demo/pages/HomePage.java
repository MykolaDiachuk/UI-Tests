package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.demo.utils.Waiter.*;

public class HomePage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "a.AppMenuItem_appMenuItem__-8c3R[href='/catalog']")
    private WebElement catalogLink;

    private static final By COOKIE_ACCEPT_BUTTON = By.id("onetrust-accept-btn-handler");

    public HomePage() {
        super();
    }

    public CatalogMainPage goToCatalog() {
        logger.info("Navigating to catalog page");
        waitForElementToBeClickable(catalogLink).click();
        return new CatalogMainPage();
    }

    public void acceptCookies() {
        try {
            WebElement acceptButton = waitForElementToBePresent(COOKIE_ACCEPT_BUTTON);
            if (acceptButton.isDisplayed()) {
                logger.info("Accepting cookies");
                acceptButton.click();
            }
        } catch (NoSuchElementException e) {
            logger.warn("Cookie accept button not found, skipping");
        }
    }
}
