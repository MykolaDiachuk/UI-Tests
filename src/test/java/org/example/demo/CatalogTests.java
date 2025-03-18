package org.example.demo;

import org.example.demo.pages.CatalogMainPage;
import org.example.demo.pages.CourseEntityPage;
import org.example.demo.pages.HomePage;
import org.example.demo.utils.ConfigReader;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.demo.enums.EstimatedEffort.ONE_TO_FOUR_HOURS;
import static org.example.demo.enums.EstimatedEffort.UP_TO_1_HOUR;
import static org.example.demo.enums.Language.*;
import static org.example.demo.enums.Skill.*;
import static org.example.demo.enums.TargetLevel.NOT_DEFINED;
import static org.example.demo.enums.TargetLevel.NOVICE;

public class CatalogTests {
    private final Logger logger = LoggerFactory.getLogger(CatalogTests.class);

    private CatalogMainPage catalogMainPage;

    @BeforeMethod
    public void setup() {
        logger.info("Initialize browser");
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getProperty("base_url"));
    }

    @BeforeMethod(dependsOnMethods = "setup")
    public void prepareTest() {
        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        catalogMainPage = homePage.goToCatalog();
    }

    @Test(description = "Verify that filters can be selected successfully on the catalog page.")
    public void verifyThatFiltersCanBeSelectedSuccessfully() {
        catalogMainPage.selectCheckbox(ENGLISH.getLabel());
        catalogMainPage.selectCheckbox(ONE_TO_FOUR_HOURS.getLabel());
        catalogMainPage.selectCheckbox(NOVICE.getLabel());

        assertThat(catalogMainPage.isCheckboxSelected(ENGLISH.getLabel())).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected(ONE_TO_FOUR_HOURS.getLabel())).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected(NOVICE.getLabel())).isTrue();

    }

    @Test(description = "Verify that skills can be selected and added for search.")
    public void verifyThatSkillsCanBeSelectedAndSearch() {
        catalogMainPage.openSkillSelection();

        catalogMainPage.getSkillSelection().addSkill(AT_JAVA.getLabel());
        catalogMainPage.getSkillSelection().addSkill(JAVA_CORE.getLabel());
        catalogMainPage.getSkillSelection().addSkill(AI_IN_TEST_AUTOMATION.getLabel());
        catalogMainPage.getSkillSelection().selectSkills();

        assertThat(catalogMainPage.isSkillSelected(AT_JAVA.getLabel())).isTrue();
        assertThat(catalogMainPage.isSkillSelected(JAVA_CORE.getLabel())).isTrue();
        assertThat(catalogMainPage.isSkillSelected(AI_IN_TEST_AUTOMATION.getLabel())).isTrue();
    }

    @Test(description = "Verify that right course page was found.")
    public void verifyThatCoursePageWasFound() {
        catalogMainPage.selectCheckbox(ENGLISH.getLabel());
        catalogMainPage.selectCheckbox(UP_TO_1_HOUR.getLabel());
        catalogMainPage.selectCheckbox(NOT_DEFINED.getLabel());

        String courseName = "Amazon Aurora Service Primer";

        CourseEntityPage courseEntityPage = catalogMainPage.goToCourse(courseName);

        assertThat(courseEntityPage.getTitle()).isEqualTo(courseName);
    }

    @Test(description = "Verify that selecting valid languages in modal.")
    public void verifyThatSelectingValidLanguages() {
        catalogMainPage.openLanguageSelectionModal();
        catalogMainPage.selectLanguages(CZECH.getLabel(), SPANISH.getLabel(), FRENCH.getLabel());

        assertThat(catalogMainPage.isLanguageSelected(CZECH.getLabel())).isTrue();
        assertThat(catalogMainPage.isLanguageSelected(SPANISH.getLabel())).isTrue();
        assertThat(catalogMainPage.isLanguageSelected(FRENCH.getLabel())).isTrue();
    }

    @Test(description = "Verify that other languages was not selected.")
    public void verifyThatOtherLanguagesWasNotSelected() {
        catalogMainPage.openLanguageSelectionModal();
        catalogMainPage.selectLanguages(ENGLISH.getLabel(), SPANISH.getLabel(), FRENCH.getLabel());

        assertThat(catalogMainPage.isLanguageSelected(ARMENIAN.getLabel())).isFalse();
        assertThat(catalogMainPage.isLanguageSelected(BELARUSIAN.getLabel())).isFalse();
        assertThat(catalogMainPage.isLanguageSelected(HEBREW.getLabel())).isFalse();
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Quit driver");
        DriverManager.quitDriver();
    }
}
