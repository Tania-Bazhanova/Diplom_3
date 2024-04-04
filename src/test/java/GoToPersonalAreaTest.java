import factory.FactoryWebDriver;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.data.GoToPageLogin;
import pom.data.PageLogin;
import pom.data.PagePersonalArea;
import stepsanddata.FakerDataForTests;
import stepsanddata.StepMethods;

public class GoToPersonalAreaTest {
    private WebDriver driver = new FactoryWebDriver().getWebDriver(System.getProperty("browser"));

    StepMethods stepMethods = new StepMethods();
    FakerDataForTests fakerData = new FakerDataForTests();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        stepMethods.registerUser(fakerData.getEmail(), fakerData.getPassword(), fakerData.getName());
    }

    @Test
    @DisplayName("Go to page Personal area")
    public void goToPersonalArea() {
        String expectedTitle = "В этом разделе вы можете изменить свои персональные данные";

        driver.get("https://stellarburgers.nomoreparties.site/");

        GoToPageLogin goToPageLogin = new GoToPageLogin(driver);
        goToPageLogin.waitButtonPersonalAreaToBeClickable();
        goToPageLogin.clickOnButtonPersonalArea();

        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.waitVisibilityOfTitleLogin();
        pageLogin.loginUser(fakerData.getEmail(), fakerData.getPassword());

        goToPageLogin.waitButtonCheckoutOrderToBeClickable();
        goToPageLogin.clickOnButtonPersonalArea();

        PagePersonalArea personalArea = new PagePersonalArea(driver);
        personalArea.waitVisibilityOfInfoTitle();

        Assert.assertEquals("Текст о ЛК не совпадает", expectedTitle, personalArea.getInfoTitle().getText());
    }

    @After
    public void afterTest() {
        driver.quit();
        stepMethods.deleteUser(fakerData.getEmail(), fakerData.getPassword());
    }
}
