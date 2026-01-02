package IMS.services;

import IMS.dtos.request.AddProductRequest;
import IMS.dtos.request.UpdateProductRequest;
import IMS.dtos.response.AddProductResponse;
import IMS.dtos.response.UpdateProductResponse;
import IMS.dtos.response.ViewProductResponse;


public interface StoreService {
    AddProductResponse AddProduct(AddProductRequest addProductRequest);
    UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest);
    ViewProductResponse viewAllProducts();
}
