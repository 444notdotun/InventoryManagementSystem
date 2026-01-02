package IMS.services;

import IMS.dtos.request.AddProductRequest;
import IMS.dtos.response.AddProductResponse;
import org.springframework.stereotype.Service;


public interface StoreService {
    AddProductResponse AddProduct(AddProductRequest addProductRequest);

}
