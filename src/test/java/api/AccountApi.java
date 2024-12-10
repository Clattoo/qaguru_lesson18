package api;

import io.qameta.allure.Step;
import models.GetBookListModel;
import models.LoginResponseModel;
import models.LoginUserModel;

import static helpers.extensions.LoginExtension.cookies;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.*;

public class AccountApi {

    @Step("Сделать запрос логина и записать ответ через API")
    public static LoginResponseModel getAuthorizationCookie() {
        LoginResponseModel response;
        LoginUserModel request = new LoginUserModel(System.getProperty("loginUser"),
                System.getProperty("passwordUser"));

        response = given(createRequestSpec)
                .body(request)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(authUserResponse200Spec)
                .extract().as(LoginResponseModel.class);

        return response;
    }

    @Step("Получить список книг через API")
    public static GetBookListModel getListOfBooks() {
        GetBookListModel response;
        response = given(createBookStoreRequestSpec)
                .header("Authorization", "Bearer " + cookies.getToken())
                .queryParam("UserId", cookies.getUserId())
                .when()
                .get("/Account/v1/User/" + cookies.getUserId())
                .then()
                .spec(authUserResponse200Spec)
                .extract().as(GetBookListModel.class);

        return response;
    }
}