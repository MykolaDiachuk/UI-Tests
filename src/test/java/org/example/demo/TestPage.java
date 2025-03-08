package org.example.demo;

import org.example.demo.pages.CatalogMainPage;
import org.example.demo.pages.CourseEntityPage;
import org.example.demo.pages.HomePage;
import org.example.demo.utils.BrowserDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

public class TestPage {
    private final Logger logger = LoggerFactory.getLogger(TestPage.class);

    private CatalogMainPage catalogMainPage;

    private static final ThreadLocal<WebDriver> thDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> thWait = new ThreadLocal<>();
    private static final ThreadLocal<FluentWait<WebDriver>> thFluentWait = new ThreadLocal<>();


    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) {
        WebDriver driver = BrowserDriverManager.getDriver(browser);
        thDriver.set(driver);

        driver.get("https://learn.epam.com/");

        thWait.set( new WebDriverWait(driver, Duration.ofSeconds(15)));
        thFluentWait.set( new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class));

        HomePage homePage = new HomePage( thDriver.get(), thWait.get(), thFluentWait.get());
        homePage.acceptCookies();
        catalogMainPage = homePage.goToCatalog();

    }

    @Test
    public void testCheckboxes() {
        catalogMainPage.selectCheckbox("English");
        catalogMainPage.selectCheckbox("1-4 hours");
        catalogMainPage.selectCheckbox("Novice");

        assertThat(catalogMainPage.isCheckboxSelected("English")).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected("1-4 hours")).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected("Novice")).isTrue();

    }

    @Test
    public void testSearch() {
        catalogMainPage.openSkillSelection();

        catalogMainPage.getSkillSelection().addSkill("AT/Java");
        catalogMainPage.getSkillSelection().addSkill("Java Core");
        catalogMainPage.getSkillSelection().addSkill("AI in Test Automation");
        catalogMainPage.getSkillSelection().selectSkills();

        assertThat(catalogMainPage.isSkillSelected("AT/Java")).isTrue();
        assertThat(catalogMainPage.isSkillSelected("Java Core")).isTrue();
        assertThat(catalogMainPage.isSkillSelected("AI in Test Automation")).isTrue();
    }

    @Test
    public void testCourse() {
        catalogMainPage.selectCheckbox("English");
        catalogMainPage.selectCheckbox("Up to 1 hour");
        catalogMainPage.selectCheckbox("Not defined");

        CourseEntityPage courseEntityPage = catalogMainPage
                .goToCourse("Amazon Bedrock Getting Started");

        assertThat(courseEntityPage.getTitle())
                .isEqualTo("Amazon Bedrock Getting Started");
    }


    @AfterMethod
    public void tearDown() {
        logger.info("Quitting driver");
        BrowserDriverManager.quitDriver();
    }
}
