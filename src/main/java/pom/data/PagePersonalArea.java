package pom.data;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class PagePersonalArea {
    WebDriver driver;

    // Локатор логотипа "Stellar Burgers"
    private By logoStellarBurgers = By.className("AppHeader_header__logo__2D0X2");

    // Локатор кнопки "Конструктор"
    private By buttonConstructor = By.xpath(".//p[text() = 'Конструктор']");

    // Локатор кнопки "Выход"
    private By buttonExit = By.xpath(".//*[text() = 'Выход']");

    // Локатор надписи "в этом разделе..." в ЛК
    private By infoTitleAboutPersonalArea = By.xpath(".//*[@class = 'Account_text__fZAIn text text_type_main-default']");

    public PagePersonalArea(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnLogo() {
        driver.findElement(logoStellarBurgers).click();
    }

    public void clickOnButtonConstructor() {
        driver.findElement(buttonConstructor).click();
    }

    public void waitButtonConstructorToBeClickable() {
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.elementToBeClickable(buttonConstructor));
    }

    public void clickOnButtonExit() {
        driver.findElement(buttonExit).click();
    }

    public WebElement getInfoTitle() {
        return driver.findElement(infoTitleAboutPersonalArea);
    }

    public void waitVisibilityOfInfoTitle() {
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.visibilityOfElementLocated(infoTitleAboutPersonalArea));
    }
}
