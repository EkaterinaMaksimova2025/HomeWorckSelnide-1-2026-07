package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class RegistrationTest {

    public String generationDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void deliveryTest() {

        String planningDate = generationDate(4, "dd.MM.yyyy");

        Selenide.open("http://localhost:9999");
        SelenideElement form = $$("form").find(Condition.visible);
        form.$("[data-test-id='city'] input").setValue("Самара");
        form.$("[data-test-id='name'] input").setValue("Семен Семеныч Горбунков");
        form.$("[data-test-id='date'] input").setValue("25.12.2026");
        form.$("[data-test-id='phone'] input").setValue("+79210055771");
        form.$("[data-test-id='agreement']").click();
        form.$$("form button").last().click();
        $(Selectors.withText("Успешно!"))
                .should(Condition.visible, Duration.ofSeconds(15));
        SelenideElement should = form.should(Condition.visible);

    }


}