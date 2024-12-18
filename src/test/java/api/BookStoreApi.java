package api;

import io.qameta.allure.Step;
import models.IsbnModel;
import models.AddBookListRequestModel;

import java.util.List;

import static helpers.extensions.LoginExtension.cookies;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.*;

public class BookStoreApi {

    @Step("Удалить все книги из корзины с помощью API")
    public BookStoreApi deleteAllBooksInCart() {

        given(createRequestSpec)
                .header("Authorization", "Bearer " + cookies.getToken())
                .queryParam("UserId", cookies.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(deleteBook204Spec);

        return this;
    }

    @Step("Добавить книгу в корзину с помощью API")
    public BookStoreApi addBookToList(String isbn) {

        IsbnModel isbnModel = new IsbnModel(isbn);
        AddBookListRequestModel request = new AddBookListRequestModel(cookies.getUserId(), List.of(isbnModel));

        given(createRequestSpec)
                .header("Authorization", "Bearer " + cookies.getToken())
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(successfulResponse201Spec);

        return this;

    }
}