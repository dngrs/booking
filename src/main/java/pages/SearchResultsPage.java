package pages;

import com.google.common.base.Predicate;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;
import utils.DriverFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Igor Odokienko.
 */
public class SearchResultsPage extends BasePage {

    By address = By.cssSelector(".address");
    By price = By.cssSelector(".price.availprice.no_rack_rate>b,.price.scarcity_color.anim_rack_rate>b");
    By overlayContainer = By.cssSelector(".sr-usp-overlay__container");

    @FindBy(xpath = "//a[contains(text(),'Lowest Price First')]")
    WebElement lowestPriceFirstTab;

    public SearchResultsPage(WebDriver driver){
        super(driver);
    }

    @Step
    public void checkHotelsLocation(String location) {
        List<WebElement> addresses = webDriverUtils.getWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(address));
        addresses.forEach(a -> Assert.assertTrue(a.getText().contains(location)));
        LOG.info("Checked hotel location");
    }

    @Step
    public SearchResultsPage clickOnLowestPriceFirstLink() {
        webDriverUtils.getWait()
                .until(ExpectedConditions.elementToBeClickable(lowestPriceFirstTab))
                .click();
        LOG.info("Clicked on 'Lowest Price First' link");
        return this;
    }

    @Step
    public void checkOffersAreSortedByPriceAsc() {

        webDriverUtils.getWait()
                .until(ExpectedConditions.invisibilityOfElementLocated(overlayContainer));

        Predicate<WebDriver> isLoaded = webDriver -> {
            List<WebElement> allPrices = webDriverUtils.getWait(30)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(price));
            return allPrices.stream()
                    .allMatch(e -> {
                        if (e.getText().isEmpty()) {
                            webDriverUtils.scrollToElement(e);
                        }
                        return !e.getText().isEmpty();
                    });
        };

        webDriverUtils.getWait()
                .until(isLoaded);

        List<WebElement> allPrices = driver.findElements(price);

        boolean sortedAscending =
                IntStream.range(0, allPrices.size() - 1)
                        .allMatch(i -> Integer.compare(parsePrice(allPrices.get(i)), parsePrice(allPrices.get(i + 1))) <= 0);

        Assert.assertTrue(sortedAscending);
        LOG.info("Checked offers are sorted by price asc: " + sortedAscending);

    }

    private Integer parsePrice(WebElement element) {
        return Integer.valueOf(element.getText().replaceAll("[^0-9]", ""));
    }

}
