import factory.FactoryWebDriver;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.data.GoToPageLogin;
import pom.data.PageConstructor;
import pom.data.PageLogin;
import pom.data.PagePersonalArea;
import stepsanddata.FakerDataForTests;
import stepsanddata.StepMethods;

public class GoToConstructorTests {
    private WebDriver driver = new FactoryWebDriver().getWebDriver(System.getProperty("browser"));

    StepMethods stepMethods = new StepMethods();
    FakerDataForTests fakerData = new FakerDataForTests();

    GoToPageLogin goToPageLogin = new GoToPageLogin(driver);
    PageLogin pageLogin = new PageLogin(driver);
    PagePersonalArea pagePersonalArea = new PagePersonalArea(driver);
    PageConstructor pageConstructor = new PageConstructor(driver);

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        stepMethods.registerUser(fakerData.getEmail(), fakerData.getPassword(), fakerData.getName());
    }

    @Test
    @DisplayName("Go to constructor by click button Constructor")
    public void goToConstructorByButtonConstructor() {
        driver.get("https://stellarburgers.nomoreparties.site");

        goToPageLogin.waitButtonPersonalAreaToBeClickable();
        goToPageLogin.clickOnButtonPersonalArea();

        pageLogin.waitVisibilityOfTitleLogin();
        pageLogin.loginUser(fakerData.getEmail(), fakerData.getPassword());

        goToPageLogin.waitButtonCheckoutOrderToBeClickable();
        goToPageLogin.clickOnButtonPersonalArea();

        pagePersonalArea.waitButtonConstructorToBeClickable();
        pagePersonalArea.clickOnButtonConstructor();

        pageConstructor.waitVisibilityTitleAssembleBurger();
        Assert.assertEquals("Текст заголовка не совпадает", "Соберите бургер", pageConstructor.findTitleAssembleBurger().getText());
    }

    @Test
    @DisplayName("Go to constructor by click Logo")
    public void goToConstructorByLogo() {
        driver.get("https://stellarburgers.nomoreparties.site");

        goToPageLogin.waitButtonPersonalAreaToBeClickable();
        goToPageLogin.clickOnButtonPersonalArea();

        pageLogin.waitVisibilityOfTitleLogin();
        pageLogin.loginUser(fakerData.getEmail(), fakerData.getPassword());

        goToPageLogin.waitButtonCheckoutOrderToBeClickable();
        goToPageLogin.clickOnButtonPersonalArea();

        pagePersonalArea.clickOnLogo();

        pageConstructor.waitVisibilityTitleAssembleBurger();
        Assert.assertEquals("Текст заголовка не совпадает", "Соберите бургер", pageConstructor.findTitleAssembleBurger().getText());
    }

    @After
    public void afterTest() {
        driver.quit();
        stepMethods.deleteUser(fakerData.getEmail(), fakerData.getPassword());
    }
}
