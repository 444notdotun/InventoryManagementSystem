package IMS.services;

import IMS.dtos.request.AddProductRequest;
import IMS.dtos.request.UpdateProductRequest;
import IMS.dtos.response.*;


public interface StoreService {
    AddProductResponse AddProduct(AddProductRequest addProductRequest);
    UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest);
    ViewProductResponse viewAllProducts();
    ViewByIdResponse viewById(String viewByIdRequest);
    DeleteResponse deleteProduct(String deleteRequest);
}
