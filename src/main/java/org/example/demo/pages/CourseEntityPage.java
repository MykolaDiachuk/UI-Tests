package org.example.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseEntityPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(CourseEntityPage.class);
    @FindBy(xpath = "//h2[contains(@class, 'CourseTitleSection_name')]")
    protected WebElement title;

    public CourseEntityPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        logger.info("Getting course title: {}", title);
        return waitForElementToBeVisible(title).getText();
    }
}
