package sit.int204.classicmodelsservice.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sit.int204.classicmodelsservice.ditos.NewCustomerDto;
import sit.int204.classicmodelsservice.ditos.PageDTO;
import sit.int204.classicmodelsservice.ditos.SimpleCustomerDTO;
import sit.int204.classicmodelsservice.exceptions.ErrorResponse;
import sit.int204.classicmodelsservice.exceptions.GeneralException;
import sit.int204.classicmodelsservice.exceptions.ItemNotFoundException;
import sit.int204.classicmodelsservice.model.Customer;
import sit.int204.classicmodelsservice.model.Order;
import sit.int204.classicmodelsservice.serviceclass.CustomerService;
import sit.int204.classicmodelsservice.serviceclass.ListMapper;

import java.util.List;
import java.util.Set;



@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{customerNumber}/order")
    public List<Order> getOrderCustomer(@PathVariable Integer customerNumber) {
        return service.findbyid(customerNumber).getOrders();

    }

//    @GetMapping("")
//    public List<Customer> getAllCustomer() { //ขึ้นmethodนี้ก่อน
//        return service.getAllCustomer();
//    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerOrder(@PathVariable Integer id) {
        Customer customer = service.findbyid(id);
        SimpleCustomerDTO simpleCustomerDTO = modelMapper.map(customer, SimpleCustomerDTO.class);
        return ResponseEntity.ok(simpleCustomerDTO); //show แค่ที่อยู่ใน field
    }

    @PostMapping("")
    public NewCustomerDto createCustomer(@Valid @RequestBody NewCustomerDto newCustomer){
        return service.createCustomer(newCustomer);
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllCustomers(
            @RequestParam(defaultValue = "false") Boolean pageable,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pagesize) {
        if (pageable) {
            Page<Customer> customerPage = service.getCustomers(page, pagesize);
            return ResponseEntity.ok(listMapper.toPageDTO(customerPage, SimpleCustomerDTO.class, modelMapper));
        } else {
            return ResponseEntity.ok(listMapper.mapList(service.getCustomers(),
                    SimpleCustomerDTO.class, modelMapper));
        }
    }
}

//    @ExceptionHandler(ItemNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ErrorResponse> handleItemNotFound (ItemNotFoundException exception , WebRequest request) {
//        ErrorResponse er = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
//                exception.getMessage(),request.getDescription(false));
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
//    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Exception handleOther (Exception exception) {
//        GeneralException generalException = new GeneralException(exception.getMessage());
//        return generalException;
//    }
//}