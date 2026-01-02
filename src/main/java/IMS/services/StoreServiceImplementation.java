package IMS.services;

import IMS.data.models.Product;
import IMS.data.models.Store;
import IMS.data.repository.ProductRepo;
import IMS.data.repository.StoreRepo;
import IMS.dtos.request.AddProductRequest;
import IMS.dtos.response.AddProductResponse;
import IMS.exception.ValidateStoreException;
import IMS.exception.validateProductNameException;
import IMS.exception.validatepriceException;
import IMS.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;

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
    private void validateProductQuantity( AddProductRequest addProductRequest){
        if(addProductRequest.getQuantity()<0){
            throw new validatepriceException("quantity can not be negative");
        }
    }

    private boolean isNew(AddProductRequest addProductRequest){
        return productRepo.existsByProductName(addProductRequest.getProductName());
    }
}
