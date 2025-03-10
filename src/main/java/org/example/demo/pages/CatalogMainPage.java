package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


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
        scrollAndClick(By.xpath(getCheckboxXPath(language)));
    }

    public void selectLanguages(String... languages) {
        getListOfLenguages().stream()
                .filter(element -> languagesContainsText(languages, element.getText()))
                .forEach(this::scrollAndClick);
    }

    private List<WebElement> getListOfLenguages() {
        return waitForAllElementsToBeVisible(By.xpath(getXPathToListOfLanguages()));
    }

    public void openLanguageSelection() {
        scrollAndClick(By.xpath(getXPathToLanguageModal()));
    }

    public void openSkillSelection() {
        scrollAndClick(By.cssSelector("input.uui-input[placeholder='Search skill']"));
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

    public boolean isCheckboxSelected(String label) {
        WebElement checkbox = waitForElementToBePresent(By.xpath(getCheckboxInputXPath(label)));
        return checkbox.isSelected();
    }

    public boolean isSkillSelected(String skillName) {
        WebElement checkbox = waitForElementToBePresent(By.xpath(getSelectedSkillCheckboxXPath(skillName)));
        return checkbox.isSelected();
    }

    public boolean isLanguageSelected(String language) {
        return getListOfLenguages().stream()
                .filter(e -> e.getText().trim().equalsIgnoreCase(language.trim()))
                .anyMatch(element ->
                        element.getAttribute("aria-checked").equals("true"));
    }

    private boolean languagesContainsText(String[] languages, String text) {
        return Arrays.stream(languages)
                .map(String::trim)
                .anyMatch(lang -> lang.equalsIgnoreCase(text.trim()));
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

    private String getXPathToLanguageModal() {
        return "//button[contains(@class, 'uui-button-box') and " +
                ".//div[text()='SHOW ALL 33 LANGUAGES']]";
    }

    private String getXPathToListOfLanguages() {
        return "//div[contains(@class, \"HaYEWG\")]//div//div[contains(@class," +
                "\"uui-flex-row e5acV8 _1z7meY -clickable VspOI7\")]";
    }


}
