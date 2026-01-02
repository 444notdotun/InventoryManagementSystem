package IMS.services;


import IMS.data.repository.ProductRepo;
import IMS.data.repository.StoreRepo;
import IMS.dtos.request.AddProductRequest;
import IMS.dtos.request.CreateStoreRequest;
import IMS.dtos.response.AddProductResponse;
import IMS.exception.ValidateStoreException;
import IMS.exception.validateProductNameException;
import IMS.exception.validatepriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
class StoreServiceImplementationTest {
    AddProductRequest addProductRequest;
    CreateStoreRequest createStoreRequest;
    @Autowired
    StoreService storeService;
    @Autowired
    UserService userService;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    StoreRepo storeRepo;
 @BeforeEach
 public void setUp(){
     productRepo.deleteAll();
     storeRepo.deleteAll();
     addProductRequest = new AddProductRequest();
     addProductRequest.setProductName("MILO");
     addProductRequest.setDescription("beverages");
     addProductRequest.setPrice(BigDecimal.valueOf(2000));
     addProductRequest.setQuantity(10);
     createStoreRequest = new CreateStoreRequest();
     createStoreRequest.setStoreName("notdotun");
 }


    @Test
    public void StoreCanAddNewProductTest(){
        userService.createStore(createStoreRequest);
        storeRepo.findFirstBy().getProducts().clear();
        assertNotNull("notnull",addProductRequest);
        AddProductResponse addProductResponse =storeService.AddProduct(addProductRequest);
        assertEquals("okay","PRODUCT ENTERED SUCCESSFULLY",addProductResponse.getMessage());
        assertEquals("productCount",1,storeRepo.findFirstBy().getProducts().size());
    }

    @Test
    public void StoreCanNotAddProductIFNotCreatedOrNamed(){

        assertThrows(ValidateStoreException.class,()->storeService.AddProduct(addProductRequest));
    }

    @Test
    public void ExistingProductCanNotBeAddedTwice(){
        userService.createStore(createStoreRequest);
        storeRepo.findFirstBy().getProducts().clear();
        assertNotNull("notnull",addProductRequest);
        AddProductResponse addProductResponse =storeService.AddProduct(addProductRequest);
        assertEquals("okay","PRODUCT ENTERED SUCCESSFULLY",addProductResponse.getMessage());
        assertEquals("productCount",1,storeRepo.findFirstBy().getProducts().size());
        addProductResponse =storeService.AddProduct(addProductRequest);
        assertEquals("notOkay","PRODUCT ENTERED ALREADY EXISTS",addProductResponse.getMessage());
    }

    @Test
    public void productWithNoNameCanNotBeAdded(){
       userService.createStore(createStoreRequest);
        addProductRequest.setProductName(null);
        assertNotNull("notnull",addProductRequest);
        assertThrows(validateProductNameException.class,()->storeService.AddProduct(addProductRequest));
    }

    @Test
    public void productWithPriceLessThan1CanNotBeAdded(){
        userService.createStore(createStoreRequest);
        addProductRequest.setPrice(BigDecimal.valueOf(0));
        assertNotNull("notnull",addProductRequest);
        assertThrows(validatepriceException.class,()->storeService.AddProduct(addProductRequest));
    }

    @Test
    public void productWithQuantityLessThan0CanNotBeAdded(){
        userService.createStore(createStoreRequest);
        addProductRequest.setQuantity(-1);
        assertNotNull("notnull",addProductRequest);
        assertThrows(validatepriceException.class,()->storeService.AddProduct(addProductRequest));
    }
    @Test
    public void ExistingProductCanBeUpdated(){
        userService.createStore(createStoreRequest);
        storeRepo.findFirstBy().getProducts().clear();
        assertNotNull("notnull",addProductRequest);
        AddProductResponse addProductResponse =storeService.AddProduct(addProductRequest);
        assertEquals("okay","PRODUCT ENTERED SUCCESSFULLY",addProductResponse.getMessage());
        assertEquals("productCount",1,storeRepo.findFirstBy().getProducts().size());
        addProductResponse =storeService.AddProduct(addProductRequest);



    }


}