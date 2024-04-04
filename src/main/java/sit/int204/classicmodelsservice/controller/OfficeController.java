package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.model.Employee;
import sit.int204.classicmodelsservice.model.Office;
import sit.int204.classicmodelsservice.serviceclass.OfficeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {
    @Autowired
    private OfficeService service;
    @GetMapping("/count")
    public Count getOfficeCount(){
        return service.getOfficeCount();
    }
    @GetMapping("/{officeCode}/employees")
    public Set<Employee> getOffficeEmployee(@PathVariable String officeCode){
        return service.getOffice(officeCode).getEmployees();
    }
    @GetMapping("")
    public List<Office> getAllOffices(@RequestParam(required = false) String[] param) {
        return service.getAllOffice(param);
    }
    @GetMapping("/{officeCode}")
    public Office getOfficeById(@PathVariable String officeCode) {
        return service.getOffice(officeCode);
    }
    @PostMapping("")
    public Office addNewOffice(@RequestBody Office office) {
        return service.createNewOffice(office);
    }
    @PutMapping("/{officeCode}")
    public Office updateOffice(@RequestBody Office office, @PathVariable String officeCode) {
        return service.updateOffice(officeCode, office);
    }
    @DeleteMapping("/{officeCode}")
    public void removeOffice(@PathVariable String officeCode) {
        service.removeOffice(officeCode);
    }
}
