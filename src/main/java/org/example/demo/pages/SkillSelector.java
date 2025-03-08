package org.example.demo.pages;

import org.example.demo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class SkillSelector extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(SkillSelector.class);
    private  WebElement modal;

    public SkillSelector() {
        super();
    }

    public void setModal(WebElement modal) {
        this.modal = modal;
    }

    public  void addSkill(String skillName) {
        scrollToElement(By.cssSelector("input[placeholder='Type text for quick search']"));

        WebElement searchInput = waitForElementToBeVisible(
                By.cssSelector("input[placeholder='Type text for quick search']"));
        searchInput.sendKeys(skillName, Keys.ENTER);

        WebElement skillOption =   DriverManager.getFluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), '" + skillName + "')]")));

        DriverManager.getFluentWait().until(
                ExpectedConditions.elementToBeClickable(skillOption)).click();

        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.BACK_SPACE);
        logger.info("Added skill: " + skillName);
    }

    public  void selectSkills() {
        WebElement selectButton = modal.findElement(
                By.xpath("//button[.//div[text()='Select']]"));
        selectButton.click();
        DriverManager.getWait().until(ExpectedConditions.invisibilityOf(modal));
    }
}