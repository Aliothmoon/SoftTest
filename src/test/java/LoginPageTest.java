import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Optional;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class LoginPageTest {

    @BeforeAll
    public static void beforeAll() {
        ChromeProperties.load();
    }

    @BeforeEach
    public void beforeEach() {
        open(ChromeProperties.TEST_URL);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "login.csv", numLinesToSkip = 1)
    public void login(String username, String password, boolean expected) {
        log.info("Username : [{}]  Password : [{}] Expected: [{}]", username, password, expected);

        switchTo().frame("body");
        switchTo().frame("navbar");

        PageHelper.USERNAME.sendKeys(Optional.ofNullable(username).orElse(""));
        PageHelper.PASSWORD.sendKeys(Optional.ofNullable(password).orElse(""));
        PageHelper.LOGIN_BTN.click();

        switchTo().parentFrame();
        switchTo().frame("info");

        if (expected) {
            String actual = PageHelper.LOGIN_SUCCESS_TIP.text();
            log.info("{}", actual);
            assertTrue(Pattern.matches("Welcome, .*?", actual));
        } else {
            String actual = PageHelper.LOGIN_ERROR_TIP.text();
            log.info("{}", actual);
            assertTrue(Pattern.matches("Web Tours Error - Incorrect Password", actual));
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "register.csv", numLinesToSkip = 1)
    public void signIn(String username, String password, String confirm, String firstName,
                       String lastName, String streetAddress, String city, boolean expected
    ) {

        switchTo().frame("body");
        switchTo().frame("info");
        PageHelper.GOTO_REGISTER_BTN.click();

        PageHelper.REGISTER_USERNAME.sendKeys(Optional.ofNullable(username).orElse(""));
        PageHelper.REGISTER_PASSWORD.sendKeys(Optional.ofNullable(password).orElse(""));
        PageHelper.REGISTER_CONFIRM.sendKeys(Optional.ofNullable(confirm).orElse(""));
        PageHelper.REGISTER_FIRSTNAME.sendKeys(Optional.ofNullable(firstName).orElse(""));
        PageHelper.REGISTER_LASTNAME.sendKeys(Optional.ofNullable(lastName).orElse(""));
        PageHelper.REGISTER_STREET_ADDRESS.sendKeys(Optional.ofNullable(streetAddress).orElse(""));
        PageHelper.REGISTER_CITY.sendKeys(Optional.ofNullable(city).orElse(""));

        PageHelper.REGISTER_SUBMIT.click();

        if (expected) {
            String actual = PageHelper.REGISTER_SUCCESS_TIP.text();
            log.info("{}", actual);
            assertTrue(Pattern.matches("Thank you, .*?", actual));
        } else {
            String actual = PageHelper.REGISTER_ERROR_TIP.text();
            log.info("{}", actual);
            assertTrue(Pattern.matches("Your.*?", actual));
        }


    }
}
