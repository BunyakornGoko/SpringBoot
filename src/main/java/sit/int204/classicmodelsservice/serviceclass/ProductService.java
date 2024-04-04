package sit.int204.classicmodelsservice.serviceclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.model.Product;
import sit.int204.classicmodelsservice.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProductByProductLine(String productLine) {
        return productRepository.findByProductLineStartsWith(productLine);
    }

    public Page<Product> findAllProduct(Double lower, Double upper, String productName , String[] sortBy, String[] direction ,int pageNo , int pageSize) {
        if (lower > upper) {
            Double temp = lower;
            lower = upper;
            upper = temp;
        }
        if (upper <= 0 && lower <= 0) {
            upper = productRepository.findFirstByOrderByPriceDesc().getPrice();
        }
        List<Sort.Order> sortOrders = new ArrayList<>();
        if (sortBy!=null || sortBy.length>0){
            for(int i=0;i<sortBy.length;i++){
                sortOrders.add(new Sort.Order((direction[i].equalsIgnoreCase("asc") ?
                        Sort.Direction.ASC : Sort.Direction.DESC), sortBy[i]));
            }
        }
        if(pageSize <= 0){
            pageSize = (int) productRepository.count();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrders));
        return productRepository.getProductByPriceBetweenAndProductNameContains(lower, upper, productName, pageable);

    }
    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }
}
