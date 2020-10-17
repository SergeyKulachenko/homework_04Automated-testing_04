package ru.netology;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    @BeforeEach
    void befor() {
        open("http://localhost:9999");
    }

    @Test
    void getTrueInputValidFormTask2() {
        $("[placeholder=Город]").setValue("Кр");
        $$("[class=popup__content] .menu-item__control")
                .last().click();
        int localDate = LocalDate.now().getMonthValue();
        int localDatePlus = LocalDate.now().plusDays(55).getMonthValue();
        String datePlus = String.valueOf(LocalDate.now().plusDays(55).getDayOfMonth());
        $("[class=input__icon]").click();
        for (; (localDatePlus - localDate) > 0; localDate++) {
            $("[class=calendar__title]").$("[class='calendar__arrow calendar__arrow_direction_right']").click();
        }
        $$("td.calendar__day").find(exactText(datePlus)).click();

        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).waitUntil(visible, 15000);
    }

}
