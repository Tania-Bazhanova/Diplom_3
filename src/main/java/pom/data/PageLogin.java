package pom.data;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class PageLogin {
    private WebDriver driver;

    // локатор заголовка "Вход"
    private By titleLogin = By.xpath(".//*[text() = 'Вход']");

    // локатор инпута "Email"
    private By email = By.xpath(".//*[text() = 'Email']/parent::*/input");

    // локатор инпута "Пароль"
    private By password = By.xpath(".//*[text() = 'Пароль']/parent::*/input");

    //Локатор кнопки "Войти"
    private By buttonLogin = By.xpath(".//*[text() = 'Войти']");

    public PageLogin(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findLoginTitle() {
        return driver.findElement(titleLogin);
    }

    public void waitVisibilityOfTitleLogin() {
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.visibilityOfElementLocated(titleLogin));
    }

    public void enterDataInFieldEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    public void enterDataInFieldPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    public void loginUser(String userEmail, String userPassword) {
        enterDataInFieldEmail(userEmail);
        enterDataInFieldPassword(userPassword);
        clickButtonLogin();
    }

}
