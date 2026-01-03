package IMS.data.controller;

import IMS.dtos.request.AddProductRequest;
import IMS.dtos.request.CreateStoreRequest;
import IMS.dtos.request.UpdateProductRequest;
import IMS.dtos.response.*;
import IMS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest updateProductRequest){
        UpdateProductResponse updateProductResponse = userService.updateProduct(updateProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateProductResponse);
    }

    @GetMapping("/viewAllProducts")
    public ResponseEntity<?> viewAllProducts(){
        ViewProductResponse viewProductResponse=userService.viewAllProducts();
        return ResponseEntity.status(HttpStatus.CREATED).body(viewProductResponse);
    }

    @GetMapping("/viewById/{productId}")
    public ResponseEntity<?> viewById(@PathVariable ("productId") String viewByIdRequest){
        ViewByIdResponse viewByIdResponse = userService.viewById(viewByIdRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(viewByIdResponse);
    }
    @DeleteMapping("/deleteProduct/{productName}")
    public ResponseEntity<?> deleteProduct(@PathVariable ("productName") String deleteProductRequest){
        DeleteResponse deleteResponse = userService.deleteProduct(deleteProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteResponse);
    }

}
