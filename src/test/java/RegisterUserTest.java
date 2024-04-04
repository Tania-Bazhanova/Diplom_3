import factory.FactoryWebDriver;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.data.PageLogin;
import pom.data.PageRegister;
import stepsanddata.FakerDataForTests;
import stepsanddata.StepMethods;

import static org.hamcrest.Matchers.equalTo;

public class RegisterUserTest {
    private WebDriver driver = new FactoryWebDriver().getWebDriver(System.getProperty("browser"));

    StepMethods stepMethods = new StepMethods();
    FakerDataForTests fakerData = new FakerDataForTests();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    @DisplayName("User registration")
    public void registerUser() {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        PageRegister pageRegister = new PageRegister(driver);
        pageRegister.waitToBeClickableFieldName();
        pageRegister.registerUser(fakerData.getName(), fakerData.getEmail(), fakerData.getPassword());

        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.waitVisibilityOfTitleLogin();
        Assert.assertEquals( "Название страницы не совпадает","Вход", pageLogin.findLoginTitle().getText());

        Response userLoginResponse = stepMethods.loginUser(fakerData.getEmail(), fakerData.getPassword());
        userLoginResponse.then().assertThat().body("success", equalTo(true));
    }

    @After
    public void afterTest() {
        driver.quit();
        stepMethods.deleteUser(fakerData.getEmail(), fakerData.getPassword());
    }
}
