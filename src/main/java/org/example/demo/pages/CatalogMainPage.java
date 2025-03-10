package org.example.demo.pages;

import org.example.demo.utils.ConfigReader;
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
        scrollAndClick(getCheckboxLocator(language));
    }

    public void selectLanguages(String... languages) {
        getListOfLenguages().stream()
                .filter(element -> languagesContainsText(languages, element.getText()))
                .forEach(this::scrollAndClick);
    }

    private List<WebElement> getListOfLenguages() {
        return waitForAllElementsToBeVisible(geListOfLanguagesLocator());
    }

    public void openLanguageSelection() {
        scrollAndClick(getLanguageModalLocator());
    }

    public void openSkillSelection() {
        scrollAndClick(getSkillSelectionLocator());
        logger.info("Open skill selector");
    }

    public CourseEntityPage goToCourse(String courseName) {
        logger.info("Go to course: {}", courseName);
        WebElement courseLink = waitForElementToBePresent(getCourseLocator(courseName));
        courseLink.click();
        return new CourseEntityPage();
    }

    public boolean isCheckboxSelected(String label) {
        WebElement checkbox = waitForElementToBePresent(getCheckboxInputLocator(label));
        return checkbox.isSelected();
    }

    public boolean isSkillSelected(String skillName) {
        WebElement checkbox = waitForElementToBePresent(getSelectedSkillCheckboxLocator(skillName));
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

    private By getCheckboxLocator(String label) {
        return By.xpath(ConfigReader.getXPath("checkbox.xpath", label));
    }

    private By getCheckboxInputLocator(String label) {
        return By.xpath(ConfigReader.getXPath("checkbox.input.xpath", label));
    }

    private By getSelectedSkillCheckboxLocator(String skillName) {
        return By.xpath(ConfigReader.getXPath("skill.checkbox.xpath", skillName));
    }

    private By getLanguageModalLocator() {
        return By.xpath(ConfigReader.getXPath("language.modal.xpath"));
    }

    private By geListOfLanguagesLocator() {
        return By.xpath(ConfigReader.getXPath("languages.list.xpath"));
    }

    private By getCourseLocator(String courseName) {
        return By.xpath(ConfigReader.getXPath("course.link.xpath", courseName));
    }

    private By getSkillSelectionLocator() {
        return By.cssSelector(ConfigReader.getXPath("skill.selection.xpath"));
    }


}
