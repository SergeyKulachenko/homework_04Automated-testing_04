package ru.netology;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

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
    void getTrueInputValidForm() throws InterruptedException {
        $("[placeholder=Город]").setValue("Краснодар");
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[class=notification__content]").waitUntil(visible, 15000)
                .shouldHave(exactTextCaseSensitive("Встреча успешно забронирована на " + inputDate));
    }

    @Test
    void errorExpectedWhenEmptyFieldCity() {
        $("[placeholder=Город]").setValue("");
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave
                (exactTextCaseSensitive("Поле обязательно для заполнения"));
    }

    @Test
    void errorExpectedWhenInputIncorrectCity() {
        $("[placeholder=Город]").setValue("Сочи");
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882223345");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave
                (exactTextCaseSensitive("Доставка в выбранный город недоступна"));
    }

    @Test
    void errorExpectedWhenEmptyFieldDate() {
        $("[placeholder=Город]").setValue("Краснодар");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date]").$("[placeholder]").setValue("");
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
        String inputDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
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
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
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
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
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
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
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
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
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
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
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
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        $("[data-test-id=name].input_type_text .input__control").setValue("Васильев Иван");
        $("[data-test-id=phone]").$("[name=phone]").setValue("+79882253345");
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement] .checkbox__text").shouldHave
                (exactTextCaseSensitive("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
