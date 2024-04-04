package pom.data;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class PageRegister {
    private WebDriver driver;

    // локатор инпута "Имя"
    private By name = By.xpath(".//*[text() = 'Имя']/parent::*/input");

    // локатор инпута "Email"
    private By email = By.xpath(".//*[text() = 'Email']/parent::*/input");

    // локатор инпута "Пароль"
    private By password = By.xpath(".//*[text() = 'Пароль']/parent::*/input");

    //Локатор кнопки "Зарегистрироваться"
    private By buttonRegister = By.xpath(".//*[text() = 'Зарегистрироваться']");

    //Локатор сообщения о неверном пароле
    private By messagePasswordError = By.xpath(".//*[@class = 'input__error text_type_main-default']");

    public PageRegister(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDataInFieldName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    public void waitToBeClickableFieldName () {
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.elementToBeClickable(name));
    }

    public void enterDataInFieldEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    public void enterDataInFieldPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    public WebElement findPasswordError() {
        return driver.findElement(messagePasswordError);
    }

    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }

    public void registerUser(String userName, String userEmail, String userPassword) {
        enterDataInFieldName(userName);
        enterDataInFieldEmail(userEmail);
        enterDataInFieldPassword(userPassword);
        clickButtonRegister();
    }

}
