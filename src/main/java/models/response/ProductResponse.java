package models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int id;
    private String title;
    private double price;
    private int stock;
    private String description;
    private String brand;
    private String category;

}
