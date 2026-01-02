package IMS.data.repository;

import IMS.data.models.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepo extends MongoRepository<Store,String> {
    Store findFirstBy();
}
