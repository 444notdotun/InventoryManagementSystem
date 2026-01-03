package IMS.services;

import IMS.data.models.Product;
import IMS.data.models.Store;
import IMS.data.repository.ProductRepo;
import IMS.data.repository.StoreRepo;
import IMS.dtos.request.AddProductRequest;
import IMS.dtos.request.UpdateProductRequest;
import IMS.dtos.response.*;
import IMS.exception.ValidateStoreException;
import IMS.exception.validateProductNameException;
import IMS.exception.validatepriceException;
import IMS.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class StoreServiceImplementation  implements StoreService{
    @Autowired
    ProductRepo productRepo;

    @Autowired
    StoreRepo storeRepo;


    @Override
    public AddProductResponse AddProduct(AddProductRequest addProductRequest) {
        validateStore(storeRepo.findFirstBy());
        validateProductName(addProductRequest);
        validateProductPrice(addProductRequest);
        validateProductQuantity(addProductRequest);
        if(isNew(addProductRequest)){
            Product product=productRepo.findByProductName(addProductRequest.getProductName());
            AddProductResponse addProductResponse = new AddProductResponse();
            addProductResponse.setMessage("PRODUCT ENTERED ALREADY EXISTS");
            addProductResponse.setProductId(product.getProductId());
            return addProductResponse;
        }
        Product product = Mapper.mapRequestToProduct(addProductRequest);
        product.setProductId(generateProductId());
        productRepo.save(product);
        Store store = storeRepo.findFirstBy();
        store.getProducts().put(product.getProductName(), product);
        storeRepo.save(store);
        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setMessage("PRODUCT ENTERED SUCCESSFULLY");
        addProductResponse.setProductId(product.getProductId());
        return addProductResponse;
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest) {
        validateStore(storeRepo.findFirstBy());
        Product product = determineFields(updateProductRequest);
        productRepo.save(product);
        Store store = storeRepo.findFirstBy();
        store.getProducts().replace(updateProductRequest.getProductName(),product);
        storeRepo.save(store);
        UpdateProductResponse updateProductResponse = new UpdateProductResponse();
        updateProductResponse.setMessage("UPDATED SUCCESSFULLY");
        return  updateProductResponse;
    }

    @Override
    public ViewProductResponse viewAllProducts() {
        validateStore(storeRepo.findFirstBy());
        StringBuilder products = new StringBuilder();
        for(Product product:storeRepo.findFirstBy().getProducts().values()) {
            products.append(product.toString());
            products.append("\n");
        }
        ViewProductResponse viewProductResponse = new ViewProductResponse();
        viewProductResponse.setProducts(products.toString());
        viewProductResponse.setMessage("ALL PRODUCTS IN STORE!");
        return viewProductResponse;
    }

    @Override
    public ViewByIdResponse viewById(String viewByIdRequest) {
        ViewByIdResponse viewByIdResponse = new ViewByIdResponse();
        viewByIdResponse.setProduct(productRepo.findByProductId(viewByIdRequest));
        viewByIdResponse.setMessage("product returned successfully");
        return viewByIdResponse;
        
    }

    @Override
    public DeleteResponse deleteProduct(String deleteRequest) {
        Store store =storeRepo.findFirstBy();
        Product product = productRepo.findByProductName(deleteRequest);
        validateProduct(product);
        validateDeletion(product);
        productRepo.delete(product);
        store.getProducts().remove(product.getProductName());
        storeRepo.save(store);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("deleted!");
        return deleteResponse;
    }

    private void validateDeletion(Product product){
        if(product.getProductQuantity()>0){
            throw new ValidateStoreException("YOU CAN NOT DELETE");
        }
    }

    private void validateProduct(Product product){
        if(product == null){
            throw  new validatepriceException("PRODUCT IS NULL");
        }
    }

    private Product determineFields(UpdateProductRequest updateProductRequest) {
        String fields = updateProductRequest.getField().toLowerCase();
        Product product=productRepo.findByProductName(updateProductRequest.getProductName());
        switch (fields) {
            case"productname"-> product.setProductName(updateProductRequest.getValue());
            case"productdescription"-> product.setProductDescription(updateProductRequest.getValue());
            case "productquantity"-> {
                validateProductQuantity(updateProductRequest);
                product.setProductQuantity(updateProductRequest.getValue1());
            }
            case "productprice"->{
                validateProductPrice(updateProductRequest);
                product.setProductPrice(updateProductRequest.getValue2());
            }
            default-> throw new ValidateStoreException("WRONG FIELDS");
        }
        product.setCreatedTime(LocalDateTime.now());
        return product;
    }

    private void validateProductName(AddProductRequest addProductRequest) {
        if(addProductRequest.getProductName() == null){
            throw new validateProductNameException("PRODUCT NAME IS INVALID");
        }
    }

    private String generateProductId(){
        SecureRandom random = new SecureRandom();
        return "PRD-"+random.nextInt(100,999);
    }
    private  void validateStore(Store store){
        if(store== null||store.getName().isEmpty()){
            throw  new ValidateStoreException("STORE NAME IS NULL");
        }
    }
    private void validateProductPrice( AddProductRequest addProductRequest){
      if(addProductRequest.getPrice().compareTo(BigDecimal.ZERO)<=0){
          throw new validatepriceException("price can not be less than or equal 0");
      }
    }
    private void validateProductPrice( UpdateProductRequest updateProductRequest){
        if(updateProductRequest.getValue2().compareTo(BigDecimal.ZERO)<=0){
            throw new validatepriceException("price can not be less than or equal 0");
        }
    }
    private void validateProductQuantity( AddProductRequest addProductRequest){
        if(addProductRequest.getQuantity()<0){
            throw new validatepriceException("quantity can not be negative");
        }
    }
    private void validateProductQuantity( UpdateProductRequest  updateProductRequest){
        if(updateProductRequest.getValue1()<0){
            throw new validatepriceException("quantity can not be negative");
        }
    }

    private boolean isNew(AddProductRequest addProductRequest){
        return productRepo.existsByProductName(addProductRequest.getProductName());
    }
}
