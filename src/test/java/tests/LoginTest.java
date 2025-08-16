package tests;


import io.restassured.response.Response;
import models.request.LoginRequest;
import models.response.LoginResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import services.AuthService;
import utils.LoginDataProvider;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTest {
    private final Logger logger=LogManager.getLogger(LoginTest.class);

    @Test(description = "Login and verify token", dataProvider = "loginData",dataProviderClass = LoginDataProvider.class)
    public void loginAndVerifyToken(String username, String password) {
        AuthService authService = new AuthService();

        LoginRequest credentials = new LoginRequest(username, password);
        Response loginResponse = authService.login(credentials);


        loginResponse.then().statusCode(200);

        LoginResponse loginResponseBody = loginResponse.as(LoginResponse.class);

        String authToken = loginResponseBody.getAccessToken();
        logger.info("Login successful for user '{}' with token: {}", username, authToken);
        loginResponse.then().body("accessToken", notNullValue());
        loginResponse.then().body("username", equalTo(username));
        logger.info("Login response: " + loginResponseBody);

    }



}
