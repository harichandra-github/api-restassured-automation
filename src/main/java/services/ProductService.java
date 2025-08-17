package services;

import io.restassured.response.Response;
import models.request.ProductRequest;
import services.base.BaseService;

import static io.restassured.RestAssured.given;

public class ProductService extends BaseService {


    public Response createProduct(ProductRequest productRequest) {

        return given().spec(getRequestSpecification()).
                body(productRequest)
                .when()
                .post("/products/add");

    }

    public Response getProduct(int productId) {
        return given()
                .spec(getRequestSpecification()).pathParams("id", productId)
                .when()
                .get("/products/{id}", productId);
    }

    public Response updateProduct(int productId, ProductRequest productRequest) {
        return given()
                .spec(getRequestSpecification()).pathParams("id", productId)
                .body(productRequest)
                .when()
                .put("/products/{id}", productId);
    }

    public Response deleteProduct(int productId) {
        return given()
                .spec(getRequestSpecification()).pathParams("id", productId)
                .when()
                .delete("/products/{id}", productId);
    }


}
