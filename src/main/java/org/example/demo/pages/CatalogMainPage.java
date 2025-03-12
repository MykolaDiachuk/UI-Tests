package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.demo.utils.Waiter.*;

import java.util.Arrays;
import java.util.List;


public class CatalogMainPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(CatalogMainPage.class);

    private final SkillSelectorModal skillSelector;

    public CatalogMainPage() {
        super();
        this.skillSelector = new SkillSelectorModal();
    }

    public SkillSelectorModal getSkillSelection() {
        return skillSelector;
    }

    public void selectCheckbox(String language) {
        scrollAndClick(getCheckboxLocator(language));
        logger.info("Select checkbox: {}", language);
    }

    public void selectLanguages(String... languages) {
        getListOfLanguages().stream()
                .filter(element -> languagesContainsText(languages, element.getText()))
                .forEach(this::scrollAndClick);
    }

    private List<WebElement> getListOfLanguages() {
        logger.info("Get list of languages");
        return waitForAllElementsToBePresent(geListOfLanguagesLocator());
    }

    public void openLanguageSelectionModal() {
        scrollAndClick(getLanguageModalLocator());
    }

    public void openSkillSelection() {
        scrollAndClick(getSkillSelectionLocator());
        logger.info("Open skill selector");
    }

    public CourseEntityPage goToCourse(String courseName) {
        WebElement courseLink = waitForElementToBePresent(getCourseLocator(courseName));
        courseLink.click();
        logger.info("Go to course: {}", courseName);
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
        try {
            WebElement languageOption = DriverManager
                    .getDriver()
                    .findElement(By.xpath("//div[@role='option'][.//div[contains(text(), '"
                            + language + "')]]"));
            return "true".equals(languageOption.getDomAttribute("aria-checked"));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean languagesContainsText(String[] languages, String text) {
        return Arrays.stream(languages)
                .map(String::trim)
                .anyMatch(lang -> lang.equalsIgnoreCase(text.trim()));
    }

    private By getCheckboxLocator(String label) {
        return By.xpath("//label[.//div[contains(@class, 'uui-input-label') and text()='" + label + "']]");
    }

    private By getCheckboxInputLocator(String label) {
        return By.xpath("//div[@role='option'][.//div[text()='" + label + "']]//input[@type='checkbox']");
    }

    private By getSelectedSkillCheckboxLocator(String skillName) {
        return By.xpath("//div[@role='option'][@aria-checked='true'][.//div[@class='uui-input-label' and text()='" + skillName + "']]//input[@type='checkbox']");
    }

    private By getLanguageModalLocator() {
        return By.xpath("//button[contains(@class, 'uui-button-box') and .//div[text()='SHOW ALL 33 LANGUAGES']]");
    }

    private By geListOfLanguagesLocator() {
        return By.xpath("//div[@role='option']//div[contains(@class, 'FMk1Jo uui-text')]");
    }

    private By getCourseLocator(String courseName) {
        return By.xpath("//div[contains(@class, 'OverflowedTypography_content__') and text()='" + courseName + "']");
    }

    private By getSkillSelectionLocator() {
        return By.cssSelector("input.uui-input[placeholder='Search skill']");
    }


}
