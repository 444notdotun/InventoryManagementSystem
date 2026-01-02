package IMS.utils;

import IMS.data.models.Product;
import IMS.data.models.Store;
import IMS.dtos.request.AddProductRequest;
import IMS.dtos.request.CreateStoreRequest;

import java.time.LocalDateTime;

public class Mapper {

    public static Product mapRequestToProduct(AddProductRequest addProductRequest){
        Product product=new Product(addProductRequest.getProductName(),addProductRequest.getQuantity(),addProductRequest.getPrice(),addProductRequest.getDescription());
        product.setCreatedTime(LocalDateTime.now());
        return product;
    }

    public static Store mapRequestToStore(CreateStoreRequest request){
        return new Store(request.getStoreName());
    }
}
