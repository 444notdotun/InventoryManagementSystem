package IMS.data.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Document
public class Product {
@ToString.Exclude
    private String productId;
    private String productName;
    private int productQuantity;
    private BigDecimal productPrice;
    private String productDescription;

    @ToString.Exclude
    @Id
    private String Id;
    @ToString.Exclude
    private LocalDateTime createdTime;

    public Product( String productName, int productQuantity, BigDecimal productPrice, String productDescription) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productDescription=productDescription;
        this.createdTime=LocalDateTime.now();
    }


}
