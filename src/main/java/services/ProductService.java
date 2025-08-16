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


}
