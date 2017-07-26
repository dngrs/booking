package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;
import utils.DriverFactory;
import utils.PropertyConfig;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by Igor Odokienko.
 */
public class HomePage extends BasePage {

    private static final String BASE_URL = PropertyConfig.getProperty("site.url");
    @FindBy(id = "ss")
    WebElement destinationPropertyNameOrAddressField;
    @FindBy(xpath = "//div[@data-placeholder='Check-out Date']")
    WebElement checkOutDatePicker;
    @FindBy(xpath = "//div[@class='leftwide rilt-left']/div[1]//span[text()='Look & book' or text()='Search']")
    WebElement searchButton;

    public HomePage(WebDriver driver){
        super(driver);
    }

    @Step
    public HomePage open() {
        driver.get(BASE_URL);
        LOG.info("Home page is opened");
        return this;
    }

    @Step
    public HomePage enterDestination(String destination) {
        webDriverUtils.getWait()
                .until(ExpectedConditions.elementToBeClickable(destinationPropertyNameOrAddressField))
                .sendKeys(destination);

        webDriverUtils.getWait()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[starts-with(@data-label, '" + destination + "')]")))
                .click();
        LOG.info("'" + destination + "'" + " is entered in 'Destination, property name or address: ' field");
        return this;
    }

    @Step
    public HomePage setCheckInDate(LocalDate localDate) {
        selectDate("in", localDate);
        LOG.info("'Check-in' date '" + localDate + "' is selected");
        return this;
    }

    @Step
    public HomePage setCheckOutDate(LocalDate localDate) {
        selectDate("out", localDate);
        LOG.info("'Check-out' date '" + localDate + "' is selected");
        return this;
    }

    @Step
    public HomePage clickOnCheckOutDatePicker() {
        webDriverUtils.getWait()
                .until(ExpectedConditions.elementToBeClickable(checkOutDatePicker))
                .click();
        LOG.info("Clicked on 'Check-out' datepicker");
        return this;
    }

    @Step
    public SearchResultsPage pressSearchButton() {
        webDriverUtils.getWait()
                .until(ExpectedConditions.elementToBeClickable(searchButton))
                .click();
        LOG.info("'Search' button is pressed");
        return new SearchResultsPage(driver);
    }

    private void selectDate(String inOrOut, LocalDate localDate) {

        String monthAndYear = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + localDate.getYear();
        WebElement monthAndYearElement;

        do {
            monthAndYearElement = driver.findElement(By.xpath("//div[@class='sb-dates__col --check" + inOrOut + "-field']//th[contains(text(), '" + monthAndYear + "')]"));
            if (!monthAndYearElement.isDisplayed()) {
                driver.findElement(By.xpath("//div[@class='sb-dates__col --check" + inOrOut + "-field']//div[@class='c2-button c2-button-further']/span"))
                        .click();
            }
        } while (!monthAndYearElement.isDisplayed());

        driver.findElement(By.xpath("//div[@class='sb-dates__col --check" + inOrOut + "-field']//th[contains(text(), '" + monthAndYear + "')]/ancestor::table//span[text()='" + localDate.getDayOfMonth() + "']"))
                .click();

    }

}
