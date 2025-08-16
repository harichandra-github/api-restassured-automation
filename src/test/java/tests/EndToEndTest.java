package tests;

import io.restassured.response.Response;
import models.request.LoginRequest;
import models.request.ProductRequest;
import models.response.LoginResponse;
import models.response.ProductResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import services.AuthService;
import services.ProductService;
import utils.ConfigReader;
import utils.ProductRequestGenerator;


@Listeners(listeners.TestListeners.class)
public class EndToEndTest {


    private final Logger logger = LogManager.getLogger(EndToEndTest.class);
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
        Assert.assertEquals(logresponse.getUsername(), username, "Username does not match expected value.");

    }

    @Test
    public void testCreateProduct() {
        ProductService productService = new ProductService();
        ProductRequestGenerator productRequestGenerator = new ProductRequestGenerator();

       ProductRequest generatedProductRequest = productRequestGenerator.generateProductRequest();

        Response response = productService.createProduct(generatedProductRequest);
        response.then().statusCode(201);
        ProductResponse productResponse = response.as(ProductResponse.class);

        Assert.assertNotNull(productResponse,"Product response should not be null.");
        Assert.assertEquals(productResponse.getTitle(), generatedProductRequest.getTitle(),
                "Product title does not match expected value.");
       Assert.assertTrue(productResponse.getId() > 0,"Product ID should be generated");
        logger.info("Product created successfully with ID: " + productResponse.getId());
        logger.info("Product response: " + productResponse);

    }
}
