package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WebDriverUtils;

import java.lang.invoke.MethodHandles;

/**
 * Created by Igor Odokienko.
 */
public abstract class BasePage {

    protected final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    protected WebDriver driver;
    protected WebDriverUtils webDriverUtils;

    public BasePage(WebDriver driver){
        this.driver = driver;
        webDriverUtils = new WebDriverUtils(driver);
        PageFactory.initElements(driver, this);
    }

}
