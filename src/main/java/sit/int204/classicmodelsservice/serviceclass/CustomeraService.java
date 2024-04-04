package sit.int204.classicmodelsservice.serviceclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.model.Customera;
import sit.int204.classicmodelsservice.repository.CustomeraRepository;

import java.util.List;

@Service
public class CustomeraService {
    @Autowired
    CustomeraRepository customeraRepository;
    public List<Customera> insertCustomers(List<Customera> customers) {
        return customeraRepository.saveAll(customers);
    }
    public  List<Customera> findAllCustomera(){
        return findAllCustomera(null);
    }
    public  List<Customera> findAllCustomera(String name){
        if(name == null || name.isEmpty() || name.isBlank()){
            return customeraRepository.findAll();
        } else {
            return customeraRepository.findByFirstNameContaining(name);
        }
    }
}
