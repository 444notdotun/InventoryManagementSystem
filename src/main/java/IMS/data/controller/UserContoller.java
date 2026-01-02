package IMS.data.controller;

import IMS.dtos.request.AddProductRequest;
import IMS.dtos.request.CreateStoreRequest;
import IMS.dtos.response.AddProductResponse;
import IMS.dtos.response.CreateStoreResponse;
import IMS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserContoller {
    @Autowired
    private UserService userService;
    @PostMapping("/createStore")
    public ResponseEntity<?> createStore(@RequestBody CreateStoreRequest  createStoreRequest){
        CreateStoreResponse createStoreResponse = userService.createStore(createStoreRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createStoreResponse);
    }
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest){
        AddProductResponse  addProductResponse = userService.AddProduct(addProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addProductResponse);
    }

}
