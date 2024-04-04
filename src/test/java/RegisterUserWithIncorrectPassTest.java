import factory.FactoryWebDriver;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pom.data.PageRegister;
import stepsanddata.FakerDataForTests;

@RunWith(Parameterized.class)
public class RegisterUserWithIncorrectPassTest {
    private WebDriver driver = new FactoryWebDriver().getWebDriver(System.getProperty("browser"));
    FakerDataForTests fakerData = new FakerDataForTests();

    private final String password;
    private final String expectedResult;

    public RegisterUserWithIncorrectPassTest(String password, String expectedResult) {
        this.password = password;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Object[][] getPasswordData() {
        return new Object[][]{
                {"p", "Некорректный пароль"},
                {"pas", "Некорректный пароль"},
                {"pass1", "Некорректный пароль"}
        };
    }

    @Test
    @DisplayName("User registration with incorrect password")
    public void registerUserWithIncorrectPassword() {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        PageRegister pageRegister = new PageRegister(driver);
        pageRegister.registerUser(fakerData.getName(), fakerData.getEmail(), password);

        Assert.assertEquals("Сообщения об ошибке не совпадают", expectedResult, pageRegister.findPasswordError().getText());
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
