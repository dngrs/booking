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
public class CheckHotelLocationOnSearchPageTest extends BaseTest {

    @Stories("Check that hotel location equals the search criteria")
    @Description("Verify that all hotels on the 1st page of search results are located in NYC")
    @Test
    public void verifyHotelsLocation() {

        HomePage homePage = new HomePage(driver);
        homePage.open()
                .enterDestination("New York City")
                .setCheckInDate(LocalDate.of(2017, Month.SEPTEMBER, 20))
                .clickOnCheckOutDatePicker()
                .setCheckOutDate(LocalDate.of(2017, Month.SEPTEMBER, 25))
                .pressSearchButton()
                .checkHotelsLocation("New York City");

    }

}
