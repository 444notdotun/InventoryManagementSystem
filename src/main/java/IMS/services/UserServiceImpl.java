package IMS.services;

import IMS.data.repository.StoreRepo;
import IMS.dtos.request.CreateStoreRequest;
import IMS.dtos.response.CreateStoreResponse;
import IMS.exception.ValidateStoreException;
import IMS.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
@Autowired
    StoreRepo storeRepo;

    @Override
    public CreateStoreResponse createStore(CreateStoreRequest request) {
        singleton();
      storeRepo.save( Mapper.mapRequestToStore(request));
      CreateStoreResponse createStoreResponse = new CreateStoreResponse();
      createStoreResponse.setMessage("STORE CREATED SUCCESSFULLY");
      return createStoreResponse;
    }

    private void singleton(){
        if(storeRepo.count()>0){
            throw new ValidateStoreException("ONLY ONE STORE CAN BE CREATED");
        }


    }
}
