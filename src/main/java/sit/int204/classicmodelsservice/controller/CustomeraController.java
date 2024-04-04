package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.model.Customera;
import sit.int204.classicmodelsservice.serviceclass.CustomeraService;

import java.util.List;

@RestController
@RequestMapping("/customeras")
public class CustomeraController {
    @Autowired
    CustomeraService service;

    @PostMapping("")
    public List<Customera> createCustomers(@RequestBody List<Customera> customeras) {
        return service.insertCustomers(customeras);
    }

    @GetMapping("")
    public List<Customera> findAllCustomer(@RequestParam(required = false) String filterString) {
        return service.findAllCustomera(filterString);
    }
}
