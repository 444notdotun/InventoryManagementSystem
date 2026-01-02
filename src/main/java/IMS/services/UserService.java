package IMS.services;

import IMS.data.models.Store;
import IMS.dtos.request.*;
import IMS.dtos.response.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface UserService {
    CreateStoreResponse createStore(CreateStoreRequest  request);
    AddProductResponse AddProduct(AddProductRequest addProductRequest);
    UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest);
    ViewProductResponse viewAllProducts();
    ViewByIdResponse viewById(ViewByIdRequest viewByIdRequest);
    DeleteResponse deleteProduct(DeleteByNameRequest deleteRequest);
}
