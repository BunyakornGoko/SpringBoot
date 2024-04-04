package sit.int204.classicmodelsservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByPriceBetweenAndProductNameContains(Double min, Double max, String productName);
    List<Product> findByProductNameContains(String productName);
    List<Product> findByProductLineStartsWith(String line);
    Product findFirstByOrderByPriceDesc();
    Page<Product> getProductByPriceBetweenAndProductNameContains(Double lower, Double upper, String partOfName, Pageable pageable);
}
