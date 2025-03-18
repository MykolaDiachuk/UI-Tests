package org.example.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.demo.utils.Waiter.waitForElementToBeVisible;

public class CourseEntityPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(CourseEntityPage.class);

    @FindBy(xpath = "//h1[contains(@class, 'Typography_typography__ViAqw Typography_h1__l4vDP primary')]")
    protected WebElement title;

    public CourseEntityPage() {
        super();
    }

    public String getTitle() {
        logger.info("Get course title: {}", title);
        return waitForElementToBeVisible(title).getText();

    }
}
