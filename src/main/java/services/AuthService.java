package services;

import io.restassured.response.Response;
import models.request.LoginRequest;
import services.base.BaseService;

import static io.restassured.RestAssured.given;

public class AuthService extends BaseService {

    public Response login(LoginRequest credentials) {

        return given().
                spec(getRequestSpecification())
                .body(credentials)
                .when()
                .post("/auth/login");

    }
}
