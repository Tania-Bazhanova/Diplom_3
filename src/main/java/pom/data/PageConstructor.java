package pom.data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageConstructor {
    WebDriver driver;

    // Локатор заголовка "Соберите бургер"
    private By titleAssembleBurger = By.xpath(".//h1[@class = 'text text_type_main-large mb-5 mt-10']");

    // Локатор переключателя раздела "Соусы"
    private By switchSauce = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Соусы']");

    // Локатор переключателя раздела "Начинки"
    private By switchFilling = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Начинки']");

    public PageConstructor(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findTitleAssembleBurger() {
        return driver.findElement(titleAssembleBurger);
    }

    public void waitVisibilityTitleAssembleBurger() {
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.visibilityOfElementLocated(titleAssembleBurger));
    }

    public void clickSwitchSauce() {
        driver.findElement(switchSauce).click();
    }

    public void clickSwitchFilling() {
        driver.findElement(switchFilling).click();
    }

    public WebElement getSelectedTab() {
       return driver.findElement(By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]"));
    }
}
