package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkillSelector extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(SkillSelector.class);

    public SkillSelector() {
        super();
    }

    public void addSkill(String skillName) {
        searchSkill(skillName);
        selectSkillFromResults(skillName);
        clearSearchInput();
        logger.info("Added skill: " + skillName);
    }

    private void searchSkill(String skillName) {
        scrollToElement(By.cssSelector("input[placeholder='Type text for quick search']"));
        WebElement searchInput = waitForElementToBeVisible(
                By.cssSelector("input[placeholder='Type text for quick search']"));
        searchInput.sendKeys(skillName, Keys.ENTER);
    }

    private void selectSkillFromResults(String skillName) {
        WebElement skillOption = DriverManager.getFluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(), '" + skillName + "')]")));

        DriverManager.getFluentWait().until(
                ExpectedConditions.elementToBeClickable(skillOption)).click();
    }

    private void clearSearchInput() {
        WebElement searchInput = waitForElementToBeVisible(
                By.cssSelector("input[placeholder='Type text for quick search']"));
        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.BACK_SPACE);
    }

    public void selectSkills() {
        WebElement selectButton = DriverManager.getDriver().findElement(
                By.xpath("//button[.//div[text()='Select']]"));
        selectButton.click();
        DriverManager.getWait().until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.uui-modal-window")));
    }


}