import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class FullFunctionTest {
    @BeforeAll
    public static void beforeAll() {
        ChromeProperties.load();
    }

    @BeforeEach
    public void beforeEach() {
        open(ChromeProperties.TEST_URL);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "flights.csv", numLinesToSkip = 10)
    public void testAll(String departureCity, String arrivalCity, String departureDate, String returnDate,
                        String nPassengers, boolean roundTripTicket, String seatingPreference,
                        String typeofSeat) throws Exception {
        //    注册
        final String username = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        final String password = "C";
        final String confirm = password;
        final String firstName = "C";
        final String lastName = "C";
        final String streetAddress = "C";
        final String city = "C";

        switchTo().frame("body");
        switchTo().frame("info");
        PageHelper.GOTO_REGISTER_BTN.click();

        PageHelper.REGISTER_USERNAME.sendKeys(username);
        PageHelper.REGISTER_PASSWORD.sendKeys(password);
        PageHelper.REGISTER_CONFIRM.sendKeys(confirm);
        PageHelper.REGISTER_FIRSTNAME.sendKeys(firstName);
        PageHelper.REGISTER_LASTNAME.sendKeys(lastName);
        PageHelper.REGISTER_STREET_ADDRESS.sendKeys(streetAddress);
        PageHelper.REGISTER_CITY.sendKeys(city);

        PageHelper.REGISTER_SUBMIT.click();

        sleep(500);

        //  登录
        switchTo().parentFrame();
        switchTo().frame("navbar");

        PageHelper.USERNAME.sendKeys(username);
        PageHelper.PASSWORD.sendKeys(password);
        PageHelper.LOGIN_BTN.click();

        switchTo().parentFrame();
        switchTo().frame("navbar");

        sleep(500);

        //        订票
        PageHelper.FLIGHTS_BTN.click();

        switchTo().parentFrame();
        switchTo().frame("info");

        PageHelper.FL_DEPT_CITY.selectOption(departureCity);
        PageHelper.FL_AR_CITY.selectOption(arrivalCity);

        PageHelper.FL_DEPT_DATE.sendKeys(Optional.ofNullable(departureDate).orElse(""));
        PageHelper.FL_RE_DATE.sendKeys(Optional.ofNullable(returnDate).orElse(""));

        PageHelper.FL_N_PASS.sendKeys(Optional.ofNullable(nPassengers).orElse(""));

        if (roundTripTicket) {
            PageHelper.FL_RT_TICKET.click();
        }

        switch (seatingPreference) {
            case "Aisle":
                $("body > blockquote > form > table > tbody > tr:nth-child(5) > td:nth-child(1) > label:nth-child(1) > input[type=radio]")
                        .click();
                break;
            case "Window":
                $("body > blockquote > form > table > tbody > tr:nth-child(5) > td:nth-child(1) > label:nth-child(3) > input[type=radio]")
                        .click();
                break;
            case "None":
                $("body > blockquote > form > table > tbody > tr:nth-child(5) > td:nth-child(1) > label:nth-child(5) > input[type=radio]")
                        .click();
                break;
        }

        switch (typeofSeat) {
            case "First":
                $("body > blockquote > form > table > tbody > tr:nth-child(5) > td:nth-child(3) > label:nth-child(1) > input[type=radio]")
                        .click();
                break;
            case "Business":
                $("body > blockquote > form > table > tbody > tr:nth-child(5) > td:nth-child(3) > label:nth-child(3) > input[type=radio]")
                        .click();
                break;
            case "Coach":
                $("body > blockquote > form > table > tbody > tr:nth-child(5) > td:nth-child(3) > label:nth-child(5) > input[type=radio]")
                        .click();
                break;
        }


        PageHelper.FL_CN_BTN.click();
        PageHelper.FL_CN_BTN1.click();
        PageHelper.FL_CN_BTN2.click();
        PageHelper.FL_CN_BTN3.click();

        switchTo().parentFrame();
        switchTo().frame("navbar");
        PageHelper.ITINERARY_BTN.click();

        switchTo().parentFrame();
        switchTo().frame("info");

        sleep(500);

        //    退票
        Iterator<SelenideElement> iterator = $$("body > blockquote > form > center > table:nth-child(1) > tbody input").iterator();
        while (iterator.hasNext()) {
            SelenideElement element = iterator.next();
            if (Objects.equals(element.attr("type"), "checkbox")) {
                element.click();
            }
        }

        PageHelper.CANCEL_CHECKED.click();

        sleep(500);

        //        退出登录
        switchTo().parentFrame();
        switchTo().frame("navbar");
        PageHelper.SIGN_OFF.click();


    }
}
