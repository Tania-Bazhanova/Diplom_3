package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FactoryWebDriver {
    public WebDriver getWebDriver(String browserName) {
        if (browserName !=null && browserName.equals("yandex")){
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/yandexdriver.exe");
            return new ChromeDriver();
        }
        return new ChromeDriver();
    }
}
