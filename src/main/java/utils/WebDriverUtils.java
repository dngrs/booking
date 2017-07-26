package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Igor Odokienko.
 */
public class WebDriverUtils {

    private WebDriver driver;
    private long explicitWait;
    private WebDriverWait wait;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        explicitWait = Long.parseLong(PropertyConfig.getProperty("wait.explicit"));
        wait = new WebDriverWait(driver, explicitWait);
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public WebDriverWait getWait(int seconds){
        return new WebDriverWait(driver, seconds);
    }

}
