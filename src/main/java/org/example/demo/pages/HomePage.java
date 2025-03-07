package org.example.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "a[href='/catalog']")
    private WebElement catalogLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CatalogMainPage goToCatalog() {
        logger.info("Navigating to catalog page");
        wait.until(ExpectedConditions.visibilityOf(catalogLink));
        waitForElementToBeClickable(catalogLink).click();

        return new CatalogMainPage(driver);
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
