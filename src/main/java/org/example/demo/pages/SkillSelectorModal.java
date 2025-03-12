package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.example.demo.utils.Waiter.*;

public class SkillSelectorModal extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(SkillSelectorModal.class);

    private final By SKILL_SEARCHER = By.cssSelector("input[placeholder='Type text for quick search']");

    public SkillSelectorModal() {
        super();
    }

    public void addSkill(String skillName) {
        searchSkill(skillName);
        selectSkillFromResults(skillName);
        clearSearchInput();
        logger.info("Added skill: " + skillName);
    }

    private void searchSkill(String skillName) {
        scrollToElement(waitForElementToBePresent(SKILL_SEARCHER));
        waitForElementToBeVisible(
                SKILL_SEARCHER).sendKeys(skillName, Keys.ENTER);
    }

    private void selectSkillFromResults(String skillName) {
        getFluentWait()
                .until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//div[contains(text(), '" + skillName + "')]")))
                .click();
    }

    private void clearSearchInput() {
        WebElement searchInput = waitForElementToBeVisible(SKILL_SEARCHER);
        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.BACK_SPACE);
    }

    public void selectSkills() {
        DriverManager.getDriver().findElement(
                By.xpath("//button[.//div[text()='Select']]")).click();
        waitForAllElementsToBeInvisible(By.cssSelector("div.uui-modal-window"));
    }


}