package sit.int204.classicmodelsservice.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.model.Product;
import sit.int204.classicmodelsservice.model.ProductPage;
import sit.int204.classicmodelsservice.serviceclass.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping ("")
    public ResponseEntity<Object> findAllProduct(
            @RequestParam(defaultValue = "") @Size(min = 5) String partOfProductName,
            @RequestParam(required = false, defaultValue = "0") Double lower,
            @RequestParam(required = false, defaultValue = "0") Double upper,
            @RequestParam(defaultValue = "") String[] sortBy,
            @RequestParam(defaultValue = "ASC") String[] sortDirection,
            @RequestParam(defaultValue = "0") @Min(0) int pageNo,
            @RequestParam(defaultValue = "10") @Min(10) int pageSize){
        if(pageSize == 0){
            return ResponseEntity.ok(service.findAllProduct());
        } else {
            Page<Product> page = service.findAllProduct(lower, upper, partOfProductName, sortBy, sortDirection, pageNo, pageSize);
            ProductPage pp = new ProductPage();
            pp.setProductList(page.getContent());
            pp.setPageNumber(page.getNumber());
            pp.setPageSize(page.getSize());
            pp.setTotalPage(page.getTotalPages());
            pp.setTotalElement((int)page.getTotalElements());
            return ResponseEntity.ok(pp);
        }
    }

    @GetMapping("/product-line/{id}")
    public List<Product> findAllProducts(@PathVariable String id){
        return service.findAllProductByProductLine(id);
    }
}
