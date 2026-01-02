package IMS.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private String Field;
    private String value;
    private int value1;
    private String productName;
    private BigDecimal Value2= BigDecimal.valueOf(0);
}
