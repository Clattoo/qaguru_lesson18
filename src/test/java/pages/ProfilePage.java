package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    @Step("Открытие страницы профиля")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Удаление одной книги из корзины пользователя через UI")
    public ProfilePage deleteOneBook() {
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
//        Selenide.refresh();
        return this;
    }

    @Step("Проверка успешности удаления книги из корзины через UI")
    public void checkDeleteBookWithUI() {
        $("#see-book-Git Pocket Guide").shouldNotBe(visible);
    }
}