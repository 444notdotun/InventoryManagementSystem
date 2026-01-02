package IMS.services;

import IMS.data.models.Store;
import IMS.dtos.request.CreateStoreRequest;
import IMS.dtos.response.CreateStoreResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface UserService {
    CreateStoreResponse createStore(CreateStoreRequest  request);
}
