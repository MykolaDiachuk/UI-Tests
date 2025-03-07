package org.example.demo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CatalogMainPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(CatalogMainPage.class);
    private SkillSelector skillSelector;

    public CatalogMainPage(WebDriver driver) {
        super(driver);
        this.skillSelector = new SkillSelector(driver);
    }

    public SkillSelector getSkillSelection() {
        return skillSelector;
    }


    public void selectCheckbox(String language) {
        logger.info("Select checkbox: {}", language);
        WebElement languageCheckbox = waitForElementToBeVisible(By.xpath(getCheckboxXPath(language)));
        clickIfNotSelected(languageCheckbox);
    }

    public boolean isCheckboxSelected(String label) {
        WebElement checkbox = waitForElementToBePresent(By.xpath(getCheckboxInputXPath(label)));
        return checkbox.isSelected();
    }

    public boolean isSkillSelected(String skillName) {
        WebElement checkbox = waitForElementToBePresent(By.xpath(getSelectedSkillCheckboxXPath(skillName)));
        return checkbox.isSelected();
    }

    private String getCheckboxXPath(String label) {
        return "//label[.//div[contains(@class, 'uui-input-label') and text()='"
                + label + "']]//div[contains(@class,'uui-input-label')]";
    }

    private String getCheckboxInputXPath(String label) {
        return "//div[@role='option'][.//div[text()='" +
                label + "']]//input[@type='checkbox']";
    }

    private String getSelectedSkillCheckboxXPath(String skillName) {
        return "//div[@role='option'][@aria-checked='true'][.//div[@class='uui-input-label' and text()='"
                + skillName + "']]//input[@type='checkbox']";
    }

    public void openSkillSelection() {
        WebElement searchInput = waitForElementToBeVisible(
                By.cssSelector("input.uui-input[placeholder='Search skill']"));
        scrollAndClick(searchInput);
        skillSelector.setModal(waitForElementToBePresent(By.cssSelector("div.uui-modal-window")));
        logger.info("Open skill selector");
    }

    public CourseEntityPage goToCourse(String courseName) {
        logger.info("Go to course: {}", courseName);
        WebElement courseLink = waitForElementToBePresent(
                By.xpath("//a[contains(@class, 'CatalogCard_itemName__LrEGP') and .//div[text()='"
                        + courseName + "']]"));
        courseLink.click();
        return new CourseEntityPage(driver);
    }


}
