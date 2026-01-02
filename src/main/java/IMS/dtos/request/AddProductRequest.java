package IMS.dtos.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class AddProductRequest {
    private String ProductName;
    private String description;
    private BigDecimal price;
    private int Quantity;
    private String StoreName;
}