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

    public HomePage() {
        super();
    }

    public CatalogMainPage goToCatalog() {
        logger.info("Navigating to catalog page");
        waitForElementToBeVisible(catalogLink);
        waitForElementToBeClickable(catalogLink).click();

        return new CatalogMainPage();
    }

    public void acceptCookies() {
        WebElement acceptButton = waitForElementToBePresent(By.id("onetrust-accept-btn-handler"));
        if (acceptButton != null && acceptButton.isDisplayed()) {
            waitForElementToBeClickable(acceptButton).click();
        }
    }
}
