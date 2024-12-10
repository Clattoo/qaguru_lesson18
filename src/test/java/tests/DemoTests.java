package tests;

import api.AccountApi;
import api.BookStoreApi;
import helpers.extensions.WithLogin;
import models.GetBookListModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("demoqa_api")

@DisplayName("Тесты книжного магазина Book Store на сайте demoqa.com")
public class DemoTests extends TestBase {
    @Test
    @WithLogin
    @DisplayName("Проверка успешного удаления книги из списка")
    void successfulDeleteBookTest() {

        BookStoreApi.deleteAllBooksInCart();
        BookStoreApi.addBookToList("9781449325862");


        ProfilePage.openPage();
        ProfilePage.deleteOneBook();
        sleep(3000);

        ProfilePage.openPage();
        ProfilePage.checkDeleteBookWithUI();


        GetBookListModel response = AccountApi.getListOfBooks();
        assertThat(response.getBooks()).isEmpty();

    }
}