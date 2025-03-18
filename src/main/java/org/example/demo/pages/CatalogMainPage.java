package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.example.demo.utils.Waiter.waitForAllElementsToBePresent;
import static org.example.demo.utils.Waiter.waitForElementToBePresent;


public class CatalogMainPage extends BasePage {
    private static final By LANGUAGE_MODAL = By.xpath("//button[contains(@class, 'uui-button-box') and .//div[text()='SHOW ALL 33 LANGUAGES']]");
    private static final By LIST_OF_LANGUAGES = By.xpath("//div[@role='option']//div[contains(@class, 'FMk1Jo uui-text')]");
    private static final By SKILL_SELECTION_INPUT = By.cssSelector("input.uui-input[placeholder='Search skill']");
    private final Logger logger = LoggerFactory.getLogger(CatalogMainPage.class);
    private final SkillSelectorModal skillSelector;


    public CatalogMainPage() {
        super();
        this.skillSelector = new SkillSelectorModal();
    }

    public SkillSelectorModal getSkillSelection() {
        return skillSelector;
    }

    public void selectCheckbox(String checkboxText) {
        logger.info("Select checkbox: {}", checkboxText);
        scrollAndClick(getCheckboxLocator(checkboxText));
    }

    public void selectLanguages(String... languages) {
        logger.info("Select languages: {}", Arrays.toString(languages));
        getListOfLanguages().stream()
                .filter(element -> languagesContainsText(languages, element.getText()))
                .forEach(this::scrollAndClick);
    }

    private List<WebElement> getListOfLanguages() {
        logger.info("Get list of languages");
        return waitForAllElementsToBePresent(LIST_OF_LANGUAGES);
    }

    public void openLanguageSelectionModal() {
        logger.info("Open language selection modal");
        scrollAndClick(LANGUAGE_MODAL);
    }

    public void openSkillSelection() {
        logger.info("Open skill selection modal");
        scrollAndClick(SKILL_SELECTION_INPUT);
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

    private By getCourseLocator(String courseName) {
        return By.xpath("//div[contains(@class, 'OverflowedTypography_content__') and text()='" + courseName + "']");
    }
}
