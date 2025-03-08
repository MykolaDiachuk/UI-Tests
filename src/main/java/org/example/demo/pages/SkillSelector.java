package org.example.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class SkillSelector extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(SkillSelector.class);
    private  WebElement modal;

    public SkillSelector(WebDriver driver, WebDriverWait wait, FluentWait<WebDriver> fluentWait) {
        super(driver , wait, fluentWait);
    }

    public void setModal(WebElement modal) {
        this.modal = modal;
    }

    public  void addSkill(String skillName) {
        WebElement searchInput = waitForElementToBeVisible(
                By.cssSelector("input[placeholder='Type text for quick search']"));
        searchInput.sendKeys(skillName, Keys.ENTER);

        WebElement skillOption = fluentWait(
                By.xpath("//div[contains(text(), '" + skillName + "')]"));

        fluentWait.until(ExpectedConditions.visibilityOf(skillOption));
        fluentWait.until(ExpectedConditions.elementToBeClickable(skillOption)).click();

        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.BACK_SPACE);
        logger.info("Added skill: " + skillName);
    }

    public  void selectSkills() {
        WebElement selectButton = modal.findElement(
                By.xpath("//button[.//div[text()='Select']]"));
        selectButton.click();
        wait.until(ExpectedConditions.invisibilityOf(modal));
        logger.info("Selected skills");
    }
}