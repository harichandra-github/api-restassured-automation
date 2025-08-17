package tests;

import io.restassured.path.json.JsonPath;
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

    @Test(description = "Login and Verify an access token")
    public void loginTest() {
        authService = new AuthService();
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        //Login and Extract Token
        LoginRequest credentials = new LoginRequest(username, password);
        Response loginResponse = authService.login(credentials);
        loginResponse.then().statusCode(200);
        LoginResponse logResponse = loginResponse.as(LoginResponse.class);
        authToken = logResponse.getAccessToken();
        logger.info("Login successful, token: " + authToken);
        logger.info(logResponse);
        Assert.assertNotNull(authToken);
        Assert.assertEquals(logResponse.getUsername(), username, "Username does not match expected value.");
    }


    // NOTE: This test simulates the creation of a new product. The product data is generated
    // using the Faker library to create a random but realistic product request. The API response
    // is verified for expected status code (201) and checks that the product details match the request.
    @Test(description = "Create a new product and verify the simulated creation")
    public void testCreateProduct() {
        ProductService productService = new ProductService();
        ProductRequestGenerator productRequestGenerator = new ProductRequestGenerator();

        ProductRequest generatedProductRequest = productRequestGenerator.generateProductRequest();

        // Simulate product creation
        Response response = productService.createProduct(generatedProductRequest);
        response.then().statusCode(201); // Expected to return a status of 201 (Created)
        ProductResponse productResponse = response.as(ProductResponse.class);

        Assert.assertNotNull(productResponse, "Product response should not be null.");
        Assert.assertEquals(productResponse.getTitle(), generatedProductRequest.getTitle(),
                "Product title does not match expected value.");
        Assert.assertTrue(productResponse.getId() > 0, "Product ID should be generated");
        logger.info("Product created successfully with ID: " + productResponse.getId());
        logger.info("Product response: " + productResponse);
    }


    // NOTE: This function is currently using hardcoded values to simulate retrieving a product.
    // It does not fetch any data from the actual server. In the future, this should be connected
    // to the backend to retrieve the real product data from the database.
    @Test(description = "Read a product by ID and verify the details", dependsOnMethods = "testCreateProduct")
    public void testReadProduct() {
        ProductService productService = new ProductService();
        Assert.assertNotNull(1, "Product ID should not be null before reading the product.");

        // Simulate reading product details
        Response response = productService.getProduct(1);
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();

        Assert.assertNotNull(jsonPath, "Product response should not be null.");
        Assert.assertEquals(jsonPath.getInt("id"), 1, "Product ID does not match expected value.");
        logger.info("Product read successfully: " + jsonPath);
    }

    // NOTE: This function is currently using hardcoded values to simulate the product update.
    // It does not update any data on the actual server. In the future, this should interact with
    // the server/database to update the product.
    @Test(description = "Update the product and verify the simulated update", dependsOnMethods = "testReadProduct")
    public void testUpdateProduct() {
        ProductService productService = new ProductService();
        Assert.assertNotNull(1, "Product ID should not be null before updating the product.");

        ProductRequestGenerator productRequestGenerator = new ProductRequestGenerator();
        ProductRequest updatedProductRequest = productRequestGenerator.generateProductRequest();

        // Simulate product update
        Response response = productService.updateProduct(1, updatedProductRequest);
        response.then().statusCode(200); // Expected to return a status of 200 (OK)
        JsonPath jsonPath = response.jsonPath();

        Assert.assertNotNull(jsonPath, "Updated product response should not be null.");
        Assert.assertEquals(jsonPath.getInt("id"), 1, "Product ID does not match expected value.");
        Assert.assertEquals(jsonPath.getString("title"), updatedProductRequest.getTitle(),
                "Updated product title does not match expected value.");
        logger.info("Product updated successfully: " + jsonPath);
    }

    // NOTE: This function is currently using hardcoded values to simulate the product deletion.
    // It does not delete any data from the actual server or database. In the future, it should
    // be implemented to interact with the backend to perform the deletion.
    @Test(description = "Delete the product and verify simulated deletion", dependsOnMethods = "testUpdateProduct")
    public void testDeleteProduct() {
        ProductService productService = new ProductService();
        Assert.assertNotNull(1, "Product ID should not be null before deleting the product.");

        // Simulate product deletion
        Response response = productService.deleteProduct(1);
        response.then().statusCode(200); // Expected to return a status of 200 (OK)

        boolean isDeleted = response.jsonPath().getBoolean("isDeleted");
        String deletedOn = response.jsonPath().getString("deletedOn");

        // Validate that the product is marked as deleted
        Assert.assertTrue(isDeleted, "Product should be marked as deleted.");
        Assert.assertNotNull(deletedOn, "Product deletion timestamp should not be null.");

        logger.info("Product deletion simulated: isDeleted = " + isDeleted + ", deletedOn = " + deletedOn);

    }
}

