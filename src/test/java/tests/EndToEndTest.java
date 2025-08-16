package tests;

import io.restassured.response.Response;
import models.request.LoginRequest;
import models.response.LoginResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import services.base.AuthService;
import utils.ConfigReader;

@Listeners(listeners.TestListeners.class)
public class EndToEndTest {


private final Logger logger= LogManager.getLogger(EndToEndTest.class);
    private AuthService authService;
    private String authToken;

    @Test
    public void loginTest() {
        authService = new AuthService();
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");


        // Step 1: Login and Extract Token
        LoginRequest credentials = new LoginRequest(username, password);
        Response loginResponse = authService.login(credentials);
        loginResponse.then().statusCode(200);
        LoginResponse logresponse = loginResponse.as(LoginResponse.class);
        authToken = logresponse.getAccessToken();
        logger.info("Login successful, token: " + authToken);
        logger.info(logresponse);
        Assert.assertNotNull(authToken);
        Assert.assertEquals(logresponse.getUsername(),username, "Username does not match expected value.");


    }
}
