package pom.data;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class GoToPageLogin {
    private WebDriver driver;

    // локатор кнопки «Войти в аккаунт» на главной странице
    private By buttonLoginInAccountOnMainPage = By.xpath(".//*[text() = 'Войти в аккаунт']");

    // локатор кнопки «Личный кабинет» хедере
    private By buttonPersonalAreaInHeader = By.xpath(".//*[text() = 'Личный Кабинет']");

    // локатор кнопки "Войти" на странице Регистрация
    private By buttonLoginOnRegisterPage = By.xpath(".//*[@class = 'Auth_link__1fOlj']");

    // локатор кнопки "Войти" на странице Восстановление пароля
    private By buttonLoginOnForgotPasswordPage = By.xpath(".//*[@class = 'Auth_link__1fOlj']");

    //локатор кнопки "Оформить заказ"
    private By buttonCheckoutOrder = By.xpath(".//*[text() = 'Оформить заказ']");

    public GoToPageLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnButtonLoginInAccount() {
        driver.findElement(buttonLoginInAccountOnMainPage).click();
    }

    public void clickOnButtonPersonalArea() {
        driver.findElement(buttonPersonalAreaInHeader).click();
    }

    public void waitButtonPersonalAreaToBeClickable() {
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.elementToBeClickable(buttonPersonalAreaInHeader));
    }

    public void clickOnButtonLoginOnRegisterPage() {
        driver.findElement(buttonLoginOnRegisterPage).click();
    }

    public void clickOnButtonLoginOnForgotPasswordPage() {
        driver.findElement(buttonLoginOnForgotPasswordPage).click();
    }
    public WebElement findButtonCheckoutOrder() {
        return driver.findElement(buttonCheckoutOrder);
    }

    public void waitButtonCheckoutOrderToBeClickable() {
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.elementToBeClickable(buttonCheckoutOrder));
    }
}
