package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "a[href='/catalog']")
    private WebElement catalogLink;

    public HomePage() {
        super();
    }

    public CatalogMainPage goToCatalog() {
        logger.info("Navigating to catalog page");
        DriverManager.getWait().until(ExpectedConditions.visibilityOf(catalogLink));
        waitForElementToBeClickable(catalogLink).click();

        return new CatalogMainPage();
    }

    public void acceptCookies() {
        try {
            WebElement acceptButton =
                    waitForElementToBePresent(By.id("onetrust-accept-btn-handler"));
            waitForElementToBeClickable(acceptButton).click();
        } catch (NoSuchElementException e) {
            logger.error("Cookies accept button not found: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
        }
    }
}
