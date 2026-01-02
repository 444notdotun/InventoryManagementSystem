package IMS.data.repository;

import IMS.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product,String> {
    boolean existsByProductName(String productName);
    Product findByProductName(String productName);
}
