package org.example.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseEntityPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(CourseEntityPage.class);
    @FindBy(xpath = "//h2[contains(@class, 'CourseTitleSection_name')]")
    protected WebElement title;

    public CourseEntityPage(WebDriver driver, WebDriverWait wait, FluentWait<WebDriver> fluentWait) {
        super(driver,wait, fluentWait);
    }



    public String getTitle() {
        logger.info("Getting course title: {}", title);
        return waitForElementToBeVisible(title).getText();
    }
}
