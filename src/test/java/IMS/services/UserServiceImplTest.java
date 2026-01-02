package IMS.services;

import IMS.data.models.Store;
import IMS.data.repository.StoreRepo;
import IMS.dtos.request.CreateStoreRequest;
import IMS.exception.ValidateStoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class UserServiceImplTest {
    CreateStoreRequest request;
   @Autowired
   UserService userService;
   @Autowired
    StoreRepo storeRepo;

   @BeforeEach
   public void setUp(){
       storeRepo.deleteAll();
       request = new CreateStoreRequest();

   }

    @Test
    public void userCanCreateStore(){
      userService.createStore(request);
        assertEquals("storecount",1L,storeRepo.count());
    }

    @Test
    public void canCreateOnlyOneStore(){
        userService.createStore(request);
        assertEquals("storecount",1L,storeRepo.count());
        assertThrows(ValidateStoreException.class,()->userService.createStore(request));
    }
}