package utils;

import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Igor Odokienko.
 */
public class DriverFactory {

    private static long implicitWait = Long.parseLong(PropertyConfig.getProperty("wait.implicit"));
    private static long scriptWait = Long.parseLong(PropertyConfig.getProperty("wait.script"));
    private static long pageWait = Long.parseLong(PropertyConfig.getProperty("wait.page"));
    private WebDriver driver;

    public DriverFactory(){
        init();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void init() {
        Browser runBrowser = EnumUtils.isValidEnum(Browser.class, PropertyConfig.getProperty("browser.name").toUpperCase()) ?
                Browser.valueOf(PropertyConfig.getProperty("browser.name").toUpperCase()) : Browser.CHROME;
        switch (runBrowser) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/drivers/chromedriver");
                driver = new ChromeDriver();
                break;
        }
        setWebDriverSettings();
    }

    private void setWebDriverSettings() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageWait, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(scriptWait, TimeUnit.SECONDS);
    }

}
