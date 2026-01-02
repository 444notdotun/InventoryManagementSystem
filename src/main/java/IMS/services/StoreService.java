package IMS.services;

import IMS.dtos.request.AddProductRequest;
import IMS.dtos.request.DeleteByNameRequest;
import IMS.dtos.request.UpdateProductRequest;
import IMS.dtos.request.ViewByIdRequest;
import IMS.dtos.response.*;
import com.mongodb.internal.bulk.DeleteRequest;


public interface StoreService {
    AddProductResponse AddProduct(AddProductRequest addProductRequest);
    UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest);
    ViewProductResponse viewAllProducts();
    ViewByIdResponse viewById(ViewByIdRequest viewByIdRequest);
    DeleteResponse deleteProduct(DeleteByNameRequest deleteRequest);
}
