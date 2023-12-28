import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Optional;

import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class FlightsTest {
    @BeforeAll
    public static void beforeAll() {
        ChromeProperties.load();
    }

    @BeforeEach
    public void beforeEach() {
        open(ChromeProperties.TEST_URL);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "flights.csv", numLinesToSkip = 1)
    public void book(String departureCity, String arrivalCity, String departureDate, String returnDate,
                     String nPassengers, boolean roundTripTicket, String seatingPreference,
                     String typeofSeat, boolean expected) {

        switchTo().frame("body");
        switchTo().frame("navbar");

        PageHelper.USERNAME.sendKeys(ChromeProperties.USER_NAME);
        PageHelper.PASSWORD.sendKeys(ChromeProperties.PASSWORD);
        PageHelper.LOGIN_BTN.click();


        switchTo().parentFrame();
        switchTo().frame("navbar");
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

        if (!expected) {
            Assertions.assertEquals($("body > h1 > font > b").text(), "Invoice");
        }
    }


}
