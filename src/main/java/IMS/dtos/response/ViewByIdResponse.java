package IMS.dtos.response;

import IMS.data.models.Product;
import lombok.Data;

@Data
public class ViewByIdResponse {
    private Product product;
    private String message;
}
