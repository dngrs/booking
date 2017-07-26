
import org.junit.Test;
import pages.HomePage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Igor Odokienko.
 */
@Features("Search")
public class CheckOffersAreSortedByPriceTest extends BaseTest {

    @Stories("Check that offers are sorted by price")
    @Description("Verify that offers in search result are sorted by price in ascending order when click on 'Lowest Price First' link")
    @Test
    public void verifySortingOrder() {
        HomePage homePage = new HomePage(driver);
        homePage.open()
                .enterDestination("New York City")
                .setCheckInDate(LocalDate.of(2017, Month.SEPTEMBER, 20))
                .pressSearchButton()
                .clickOnLowestPriceFirstLink()
                .checkOffersAreSortedByPriceAsc();
    }

}
