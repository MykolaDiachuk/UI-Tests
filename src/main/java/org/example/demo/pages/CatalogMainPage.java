package org.example.demo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CatalogMainPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(CatalogMainPage.class);
    private final SkillSelector skillSelector;

    public CatalogMainPage() {
        super();
        this.skillSelector = new SkillSelector();
    }

    public SkillSelector getSkillSelection() {
        return skillSelector;
    }

    public void selectCheckbox(String language) {
        logger.info("Select checkbox: {}", language);
        clickIfNotSelected(By.xpath(getCheckboxXPath(language)));
    }

    public boolean isCheckboxSelected(String label) {
        WebElement checkbox = waitForElementToBePresent(By.xpath(getCheckboxInputXPath(label)));
        return checkbox.isSelected();
    }

    public boolean isSkillSelected(String skillName) {
        WebElement checkbox = waitForElementToBePresent(By.xpath(getSelectedSkillCheckboxXPath(skillName)));
        return checkbox.isSelected();
    }

    public void openSkillSelection() {
        scrollAndClick(By.cssSelector("input.uui-input[placeholder='Search skill']"));
        skillSelector.setModal(waitForElementToBePresent(By.cssSelector("div.uui-modal-window")));
        logger.info("Open skill selector");
    }
    public CourseEntityPage goToCourse(String courseName) {
        logger.info("Go to course: {}", courseName);
        WebElement courseLink = waitForElementToBePresent(
                By.xpath("//a[contains(@class, 'CatalogCard_itemName__LrEGP') and .//div[text()='"
                        + courseName + "']]"));
        courseLink.click();
        return new CourseEntityPage();
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


}
