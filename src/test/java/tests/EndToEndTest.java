package tests;

import io.restassured.response.Response;
import models.request.LoginRequest;
import models.response.LoginResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import services.base.AuthService;

public class EndToEndTest {
private final Logger logger= LogManager.getLogger(EndToEndTest.class);
    private AuthService authService;
    private String authToken;

    @Test
    public void sampleTest() {
        authService = new AuthService();


        // Step 1: Login and Extract Token
        LoginRequest credentials = new LoginRequest("michaelw", "michaelwpass");
        Response loginResponse = authService.login(credentials);
        loginResponse.then().statusCode(200);
        authToken = loginResponse.as(LoginResponse.class).getAccessToken();
        logger.info("Login successful, token: " + authToken);
        System.out.println(loginResponse.asPrettyString());

    }
}
