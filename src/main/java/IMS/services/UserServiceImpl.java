package IMS.services;

import IMS.data.repository.StoreRepo;
import IMS.dtos.request.*;
import IMS.dtos.response.*;
import IMS.exception.ValidateStoreException;
import IMS.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
@Autowired
    StoreRepo storeRepo;
@Autowired
StoreService storeService;

    @Override
    public CreateStoreResponse createStore(CreateStoreRequest request) {
        singleton();
      storeRepo.save( Mapper.mapRequestToStore(request));
      CreateStoreResponse createStoreResponse = new CreateStoreResponse();
      createStoreResponse.setMessage("STORE CREATED SUCCESSFULLY");
      return createStoreResponse;
    }

    @Override
    public AddProductResponse AddProduct(AddProductRequest addProductRequest) {
        return storeService.AddProduct(addProductRequest);
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest) {
        return storeService.updateProduct(updateProductRequest);
    }

    @Override
    public ViewProductResponse viewAllProducts() {
        return storeService.viewAllProducts();
    }

    @Override
    public ViewByIdResponse viewById(ViewByIdRequest viewByIdRequest) {
        return storeService.viewById(viewByIdRequest);
    }

    @Override
    public DeleteResponse deleteProduct(DeleteByNameRequest deleteRequest) {
        return storeService.deleteProduct(deleteRequest);
    }

    private void singleton(){
        if(storeRepo.count()>0){
            throw new ValidateStoreException("ONLY ONE STORE CAN BE CREATED");
        }


    }
}
