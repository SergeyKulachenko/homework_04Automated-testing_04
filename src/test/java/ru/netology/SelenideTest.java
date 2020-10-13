package ru.netology;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import javax.annotation.processing.Completion;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    void getTrueInputValidFormTask2V1() {
        $("[placeholder=Город]").setValue("Кр");
        $$("[class='menu menu_size_m menu_group-view_default menu_mode_radio-check menu_theme_alfa-on-white input__menu'] .menu-item__control")
                .find(exactText("Красноярск")).click();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(4).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).waitUntil(visible, 15000);
    }

    @Test
    void getTrueInputValidFormTask2V2() {
        $("[placeholder=Город]").setValue("Кр");
        $$("[class='menu menu_size_m menu_group-view_default menu_mode_radio-check menu_theme_alfa-on-white input__menu'] .menu-item__control")
                .last().click();
        LocalDate localDate = LocalDate.now();

//        $("[class=input__icon]").click();
//        localDate.getDayOfMonth();
//        $("[class=calendar-input__calendar-wrapper]").$("[data-day=1603314000000]").click();
//        String a=$("[class=calendar-input__calendar-wrapper]").getValue();
//        String aa=$("[class=calendar-input__calendar-wrapper]").getText();
//        String s=$("[class=popup__container]").getText();
//        String ds=$("[class=popup__container]").getSelectedText();
//        String ww=$("[class='calendar-input__calendar-wrapper']").getSelectedValue();
//        $("[data-step='1']").click();

        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(4).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).waitUntil(visible, 15000);
    }

}
