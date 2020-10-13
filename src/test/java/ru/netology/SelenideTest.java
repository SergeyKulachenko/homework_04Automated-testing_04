package ru.netology;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Click;
import com.codeborne.selenide.commands.DoubleClick;
import com.codeborne.selenide.impl.SelenideElementListProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    @BeforeEach
    void befor() {
        open("http://localhost:9999");
    }

    @Test
    void getTrueInputValidForm() {
        $("[placeholder=Город]").setValue("Краснодар");
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
    void errorExpectedWhenEmptyFieldCity() {
        $("[placeholder=Город]").setValue("");
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
        $("[class='form-field form-field_size_m form-field_theme_alfa-on-white'] .input__sub").shouldHave
                (exactTextCaseSensitive("Поле обязательно для заполнения"));
    }

    @Test
    void errorExpectedWhenInputIncorrectCity() {
        $("[placeholder=Город]").setValue("Сочи");
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
        $("[class='form-field form-field_size_m form-field_theme_alfa-on-white'] .input__sub").shouldHave
                (exactTextCaseSensitive("Доставка в выбранный город недоступна"));
    }

    @Test
    void errorExpectedWhenEmptyFieldDate() {
        $("[placeholder=Город]").setValue("Краснодар");
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']").$("[placeholder]").setValue("");
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave
                (exactTextCaseSensitive("Неверно введена дата"));
    }

    @Test
    void errorExpectedWhenInputIncorrectDate() {
        $("[placeholder=Город]").setValue("Краснодар");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(1).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave
                (exactTextCaseSensitive("Заказ на выбранную дату невозможен"));
    }

    @Test
    void errorExpectedWhenEmptyFieldName() {
        $("[placeholder=Город]").setValue("Краснодар");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(4).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldHave
                (exactTextCaseSensitive("Поле обязательно для заполнения"));
    }

    @Test
    void errorExpectedWhenInputIncorrectNameNoRu() {
        $("[placeholder=Город]").setValue("Краснодар");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(4).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Nik");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldHave
                (exactTextCaseSensitive("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void errorExpectedWhenInputIncorrectNameNoAsPasport() {
        $("[placeholder=Город]").setValue("Краснодар");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(4).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Сергей");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldHave
                (exactTextCaseSensitive("Укажите точно как в паспорте"));
    }

    @Test
    void errorExpectedWhenEmptyFieldTel() {
        $("[placeholder=Город]").setValue("Краснодар");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(4).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub").shouldHave
                (exactTextCaseSensitive("Поле обязательно для заполнения"));
    }

    @Test
    void errorExpectedWhenInputIncorrectTel() {
        $("[placeholder=Город]").setValue("Краснодар");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(4).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+7 9882253345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub").shouldHave
                (exactTextCaseSensitive("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }


    @Test
    void errorExpectedWhenUncheckedCheckbox() {
        $("[placeholder=Город]").setValue("Краснодар");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fOut = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String inputDate = localDate.plusDays(4).format(fOut);
        SelenideElement data = $("[class='calendar-input calendar-input_width_available calendar-input_theme_alfa-on-white']");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882253345");
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white input_invalid'] .checkbox__text").shouldHave
                (exactTextCaseSensitive("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
