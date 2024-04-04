import factory.FactoryWebDriver;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.data.PageConstructor;

public class GoToConstructorSectionsTests {

    private WebDriver driver = new FactoryWebDriver().getWebDriver(System.getProperty("browser"));

    @Test
    @DisplayName("Go to constructors section Bun")
    public void goToConstructorSectionBun() {
        driver.get("https://stellarburgers.nomoreparties.site");

        PageConstructor pageConstructor = new PageConstructor(driver);
        pageConstructor.waitVisibilityTitleAssembleBurger();

        Assert.assertEquals("Названия табов не совпадают", "Булки", pageConstructor.getSelectedTab().getText());
    }

    @Test
    @DisplayName("Go to constructors section Sauce")
    public void goToConstructorSectionSause() {
        driver.get("https://stellarburgers.nomoreparties.site");

        PageConstructor pageConstructor = new PageConstructor(driver);
        pageConstructor.waitVisibilityTitleAssembleBurger();

        pageConstructor.clickSwitchSauce();

        Assert.assertEquals("Названия табов не совпадают", "Соусы", pageConstructor.getSelectedTab().getText());
    }

    @Test
    @DisplayName("Go to constructors section Filling")
    public void goToConstructorSectionsFilling() {
        driver.get("https://stellarburgers.nomoreparties.site");

        PageConstructor pageConstructor = new PageConstructor(driver);
        pageConstructor.waitVisibilityTitleAssembleBurger();

        pageConstructor.clickSwitchFilling();

        Assert.assertEquals("Названия табов не совпадают","Начинки", pageConstructor.getSelectedTab().getText());
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
