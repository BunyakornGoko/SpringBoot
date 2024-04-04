package sit.int204.classicmodelsservice.serviceclass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.classicmodelsservice.ditos.NewCustomerDto;
import sit.int204.classicmodelsservice.exceptions.ItemNotFoundException;
import sit.int204.classicmodelsservice.model.Customer;
import sit.int204.classicmodelsservice.repository.CustomerRepository;
import sit.int204.classicmodelsservice.repository.EmployeeRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ListMapper listMapper;
    public List<Customer> getAllCustomer(){
        return repository.findAll();
    }

    public Customer findbyid(Integer customerNumber) { //findbyid
//        return repository.findById(customerNumber).orElseThrow(
//                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Number" + customerNumber + " DOES NOT EXIST !!!") {
//                }
//        );
        return repository.findById(customerNumber).orElseThrow(
                ()-> new ItemNotFoundException("Customer number" +" "+ "DOES NOT EXIST!!!")
        );
    }

    public Page<Customer> getCustomers(int page, int size){
        String x = null;
        x.toLowerCase();
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<Customer> getCustomers(){
        return repository.findAll();
    }

    public NewCustomerDto createCustomer(NewCustomerDto newCustomer) {
        if(repository.existsById(newCustomer.getCustomerNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate customer for id "+
                    newCustomer.getCustomerNumber()); }
        Customer customer = mapper.map(newCustomer, Customer.class);
        return mapper.map(repository.saveAndFlush(customer), NewCustomerDto.class);
    }
    public List<NewCustomerDto> getAllCustomers() {
        return listMapper.mapList(repository.findAll(), NewCustomerDto.class, mapper); }
}
