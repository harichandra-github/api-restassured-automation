package utils;

import com.github.javafaker.Faker;
import models.request.ProductRequest;

import java.sql.SQLOutput;

public class ProductRequestGenerator {


    public ProductRequest generateProductRequest() {
        Faker faker = new Faker();
        ProductRequest product= new ProductRequest();


        product.setTitle(faker.commerce().productName());
        product.setDescription(faker.lorem().paragraph());
        product.setCategory(faker.commerce().department());
        product.setPrice(Double.parseDouble(faker.commerce().price()));
        product.setStock(faker.number().numberBetween(1, 100));
        product.setBrand(faker.company().name());
        product.setSku(faker.commerce().promotionCode());
        product.setWeight(100.00);
        product.setTags(faker.lorem().words(3));

        return product;
    }

}
