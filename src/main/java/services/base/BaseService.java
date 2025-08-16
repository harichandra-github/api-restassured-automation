package services.base;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class BaseService {
    protected static final String BASE_URI= ConfigReader.getProperty("baseUrl");


    protected RequestSpecification getRequestSpecification() {
        return   given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }


}
