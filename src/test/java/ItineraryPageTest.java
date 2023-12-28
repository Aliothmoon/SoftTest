import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Iterator;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class ItineraryPageTest {
    @BeforeAll
    public static void beforeAll() {
        ChromeProperties.load();
    }

    @BeforeEach
    public void beforeEach() {
        open(ChromeProperties.TEST_URL);
    }

    @Test
    public void cancelAll() {
        switchTo().frame("body");
        switchTo().frame("navbar");

        PageHelper.USERNAME.sendKeys(ChromeProperties.USER_NAME);
        PageHelper.PASSWORD.sendKeys(ChromeProperties.PASSWORD);
        PageHelper.LOGIN_BTN.click();


        switchTo().parentFrame();
        switchTo().frame("navbar");
        PageHelper.ITINERARY_BTN.click();

        PageHelper.CANCEL_ALL.click();

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 9, 10})
    public void cancel(int i) {
        log.info("{}", i);
        switchTo().frame("body");
        switchTo().frame("navbar");

        PageHelper.USERNAME.sendKeys(ChromeProperties.USER_NAME);
        PageHelper.PASSWORD.sendKeys(ChromeProperties.PASSWORD);
        PageHelper.LOGIN_BTN.click();


        switchTo().parentFrame();
        switchTo().frame("navbar");
        PageHelper.ITINERARY_BTN.click();

        switchTo().parentFrame();
        switchTo().frame("info");


        Iterator<SelenideElement> iterator = $$("body > blockquote > form > center > table:nth-child(1) > tbody input").iterator();
        int cnt = 1;
        while (iterator.hasNext()) {
            SelenideElement element = iterator.next();
            if (Objects.equals(element.attr("type"), "checkbox")) {
                if (cnt == i) {
                    element.click();
                }
                cnt++;

            }
        }


        PageHelper.CANCEL_CHECKED.click();

    }

}

