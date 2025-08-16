package models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
        private String title;
        private String description;
        private String category;
        private double price;
        private int stock;
        private String brand;
        private String sku;
        private double weight;
        private List<String> tags;

        @Override
        public String toString() {
                return "ProductRequest{" +
                        "title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", category='" + category + '\'' +
                        ", price=" + price +
                        ", stock=" + stock +
                        ", brand='" + brand + '\'' +
                        ", sku='" + sku + '\'' +
                        ", weight=" + weight +
                        ", tags=" + tags +
                        '}';
        }
}
