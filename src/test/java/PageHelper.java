import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PageHelper {
    public static final SelenideElement USERNAME =
            $("body > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input[type=text]");
    public static final SelenideElement PASSWORD =
            $("body > form > table > tbody > tr:nth-child(6) > td:nth-child(2) > input[type=password]");
    public static final SelenideElement LOGIN_BTN =
            $("body > form > table > tbody > tr:nth-child(8) > td:nth-child(2) > input[type=image]");

    public static final SelenideElement LOGIN_ERROR_TIP =
            $("body > blockquote > center > h3 > font");
    public static final SelenideElement LOGIN_SUCCESS_TIP =
            $("body > blockquote");

    public static final SelenideElement GOTO_REGISTER_BTN =
            $("body > table > tbody > tr:nth-child(3) > td:nth-child(1) > blockquote > a:nth-child(4) > b");
    public static final SelenideElement REGISTER_SUBMIT =
            $("body > blockquote > form > table > tbody > tr:nth-child(10) > td > input[type=image]");
    public static final SelenideElement REGISTER_USERNAME =
            $("body > blockquote > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > input[type=text]");
    public static final SelenideElement REGISTER_PASSWORD =
            $("body > blockquote > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > input[type=password]");
    public static final SelenideElement REGISTER_CONFIRM =
            $("body > blockquote > form > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type=password]");
    public static final SelenideElement REGISTER_FIRSTNAME =
            $("body > blockquote > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > input[type=text]");
    public static final SelenideElement REGISTER_LASTNAME =
            $("body > blockquote > form > table > tbody > tr:nth-child(6) > td:nth-child(2) > input[type=text]");
    public static final SelenideElement REGISTER_STREET_ADDRESS =
            $("body > blockquote > form > table > tbody > tr:nth-child(7) > td:nth-child(2) > input[type=text]");
    public static final SelenideElement REGISTER_CITY =
            $("body > blockquote > form > table > tbody > tr:nth-child(8) > td:nth-child(2) > input[type=text]");

    public static final SelenideElement REGISTER_SUCCESS_TIP = $("body > blockquote");
    public static final SelenideElement REGISTER_ERROR_TIP = $("body > blockquote > center > h3 > font");

    public static final SelenideElement FLIGHTS_BTN = $("body > center > center > a:nth-child(1) > img");
    public static final SelenideElement ITINERARY_BTN = $("body > center > center > a:nth-child(3) > img");


    public static final SelenideElement FL_DEPT_CITY =
            $("body > blockquote > form > table > tbody > tr:nth-child(1) > td:nth-child(2) > select");
    public static final SelenideElement FL_AR_CITY =
            $("body > blockquote > form > table > tbody > tr:nth-child(2) > td:nth-child(2) > select");
    public static final SelenideElement FL_DEPT_DATE =
            $("body > blockquote > form > table > tbody > tr:nth-child(1) > td:nth-child(4) > input[type=text]");
    public static final SelenideElement FL_RE_DATE =
            $("body > blockquote > form > table > tbody > tr:nth-child(2) > td:nth-child(4) > input[type=text]");
    public static final SelenideElement FL_N_PASS =
            $("body > blockquote > form > table > tbody > tr:nth-child(3) > td:nth-child(2) > input[type=text]");
    public static final SelenideElement FL_RT_TICKET =
            $("body > blockquote > form > table > tbody > tr:nth-child(3) > td:nth-child(3) > label");
    public static final SelenideElement FL_CN_BTN = $("body > blockquote > form > table > tbody > tr:nth-child(7) > td > input[type=image]");
    public static final SelenideElement FL_CN_BTN1 =
            $("body > blockquote > form > center > center > table > tbody > tr > td:nth-child(1) > input[type=image]");
    public static final SelenideElement FL_CN_BTN2 = $("body > blockquote > form > center > table > tbody > tr > td:nth-child(1) > input[type=image]");
    public static final SelenideElement FL_CN_BTN3 = $("body > blockquote > center > table:nth-child(7) > tbody > tr > td > form > input[type=image]");
    public static final SelenideElement CANCEL_ALL = $("body > blockquote > form > center > table:nth-child(2) > tbody > tr > td:nth-child(2) > input[type=image]");
    public static final SelenideElement CANCEL_CHECKED = $("body > blockquote > form > center > table:nth-child(2) > tbody > tr > td:nth-child(1) > input[type=image]");
    public static final SelenideElement SIGN_OFF = $("body > center > center > a:nth-child(7) > img");
}
