package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    @Step("Открытие страницы профиля")
    public static void openPage() {
        open("/profile");
    }

    @Step("Удаление одной книги из корзины пользователя")
    public static void deleteOneBook() {
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
    }

    @Step("Проверка успешности удаления книги из корзины")
    public static void checkDeleteBookWithUI() {
        $("#see-book-Git Pocket Guide").shouldNotBe(visible);
    }


}