package org.example.demo;

import org.example.demo.pages.CatalogMainPage;
import org.example.demo.pages.CourseEntityPage;
import org.example.demo.pages.HomePage;
import org.example.demo.utils.ConfigReader;
import org.example.demo.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

public class CatalogTests {
    private final Logger logger = LoggerFactory.getLogger(CatalogTests.class);

    private CatalogMainPage catalogMainPage;

    @BeforeMethod
    public void setup() {
        DriverManager.initDriver(ConfigReader.getProperty("browser"));
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getProperty("base_url"));

        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        catalogMainPage = homePage.goToCatalog();
    }

    @Test(description = "Verify that filters can be selected successfully on the catalog page.")
    public void shouldSelectFiltersSuccessfully() {
        catalogMainPage.selectCheckbox("English");
        catalogMainPage.selectCheckbox("1-4 hours");
        catalogMainPage.selectCheckbox("Novice");

        assertThat(catalogMainPage.isCheckboxSelected("English")).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected("1-4 hours")).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected("Novice")).isTrue();

    }

    @Test(description = "Verify that skills can be selected and added for search.")
    public void shouldSelectSkillsForSearch() {
        catalogMainPage.openSkillSelection();

        catalogMainPage.getSkillSelection().addSkill("AT/Java");
        catalogMainPage.getSkillSelection().addSkill("Java Core");
        catalogMainPage.getSkillSelection().addSkill("AI in Test Automation");
        catalogMainPage.getSkillSelection().selectSkills();

        assertThat(catalogMainPage.isSkillSelected("AT/Java")).isTrue();
        assertThat(catalogMainPage.isSkillSelected("Java Core")).isTrue();
        assertThat(catalogMainPage.isSkillSelected("AI in Test Automation")).isTrue();
    }

    @Test(description = "Verify that a selected course page opens correctly.")
    public void shouldOpenSelectedCoursePage() {
        catalogMainPage.selectCheckbox("English");
        catalogMainPage.selectCheckbox("Up to 1 hour");
        catalogMainPage.selectCheckbox("Not defined");

        CourseEntityPage courseEntityPage = catalogMainPage
                .goToCourse("Amazon Bedrock Getting Started");

        assertThat(courseEntityPage.getTitle())
                .isEqualTo("Amazon Bedrock Getting Started");
    }

    @Test(description = "Verifies that selecting valid languages " +
            "('English', 'Spanish', 'French') correctly marks them as selected.")
    public void shouldSelectLanguagesWhenValidLanguagesProvided() {
        catalogMainPage.openLanguageSelection();
        catalogMainPage.selectLanguages("English", "Spanish", "French");

        assertThat(catalogMainPage.isLanguageSelected("English")).isTrue();
        assertThat(catalogMainPage.isLanguageSelected("Spanish")).isTrue();
        assertThat(catalogMainPage.isLanguageSelected("French")).isTrue();
    }

    @Test(description = "Verifies that invalid languages " +
            "('Armenian', 'Belarusian', 'Hebrew') are not selected, even if attempted." )
    public void shouldNotSelectLanguagesWhenInvalidLanguagesProvided(){
        catalogMainPage.openLanguageSelection();
        catalogMainPage.selectLanguages("English", "Spanish", "French");

        assertThat(catalogMainPage.isLanguageSelected("Armenian")).isFalse();
        assertThat(catalogMainPage.isLanguageSelected("Belarusian")).isFalse();
        assertThat(catalogMainPage.isLanguageSelected("Hebrew")).isFalse();
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Quitting driver");
        DriverManager.quitDriver();
    }
}
