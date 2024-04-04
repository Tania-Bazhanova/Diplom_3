import factory.FactoryWebDriver;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.data.GoToPageLogin;
import pom.data.PageLogin;
import stepsanddata.FakerDataForTests;
import stepsanddata.StepMethods;

import java.time.Duration;

public class LoginUserTests {
    private WebDriver driver = new FactoryWebDriver().getWebDriver(System.getProperty("browser"));

    StepMethods stepMethods = new StepMethods();
    FakerDataForTests fakerData = new FakerDataForTests();
    PageLogin pageLogin = new PageLogin(driver);
    GoToPageLogin goToPageLogin = new GoToPageLogin(driver);

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        stepMethods.registerUser(fakerData.getEmail(), fakerData.getPassword(), fakerData.getName());
    }

    @Test
    @DisplayName("Login user on click button \"Login in account\"")
    public void loginUserOnButtonLoginInAccount() {
        driver.get("https://stellarburgers.nomoreparties.site/");

        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.elementToBeClickable(goToPageLogin.getButtonLoginInAccountOnMainPage()));
        goToPageLogin.clickOnButtonLoginInAccount();

        pageLogin.loginUser(fakerData.getEmail(), fakerData.getPassword());
        goToPageLogin.waitButtonCheckoutOrderToBeClickable();

        Assert.assertEquals("Название кнопки не совпадает", "Оформить заказ", goToPageLogin.findButtonCheckoutOrder().getText());
    }

    @Test
    @DisplayName("Login user on click button \"Personal area\"")
    public void loginUserOnButtonPersonalArea() {
        driver.get("https://stellarburgers.nomoreparties.site/");

        goToPageLogin.waitButtonPersonalAreaToBeClickable();
        goToPageLogin.clickOnButtonPersonalArea();

        pageLogin.loginUser(fakerData.getEmail(), fakerData.getPassword());
        goToPageLogin.waitButtonCheckoutOrderToBeClickable();

        Assert.assertEquals("Название кнопки не совпадает", "Оформить заказ", goToPageLogin.findButtonCheckoutOrder().getText());
    }

    @Test
    @DisplayName("Login user on click button \"Login\" on Register page")
    public void loginUserOnButtonLoginOnRegisterPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.elementToBeClickable(goToPageLogin.getButtonLoginOnRegisterPage()));
        goToPageLogin.clickOnButtonLoginOnRegisterPage();

        pageLogin.loginUser(fakerData.getEmail(), fakerData.getPassword());
        goToPageLogin.waitButtonCheckoutOrderToBeClickable();

        Assert.assertEquals("Название кнопки не совпадает", "Оформить заказ", goToPageLogin.findButtonCheckoutOrder().getText());
    }

    @Test
    @DisplayName("Login user on click button \"Login\" on Forgot password page")
    public void loginUserOnButtonLoginOnForgotPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.elementToBeClickable(goToPageLogin.getButtonLoginOnForgotPasswordPage()));
        goToPageLogin.clickOnButtonLoginOnForgotPasswordPage();

        pageLogin.loginUser(fakerData.getEmail(), fakerData.getPassword());
        goToPageLogin.waitButtonCheckoutOrderToBeClickable();

        Assert.assertEquals("Название кнопки не совпадает", "Оформить заказ", goToPageLogin.findButtonCheckoutOrder().getText());
    }

    @After
    public void afterTest() {
        driver.quit();
        stepMethods.deleteUser(fakerData.getEmail(), fakerData.getPassword());
    }
}
