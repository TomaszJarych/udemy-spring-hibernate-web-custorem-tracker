package tj.udemy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tj.udemy.commons.errorhandling.CustomerNotFoundException;
import tj.udemy.entity.Customer;
import tj.udemy.service.CustomerService;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(path = "/customers", produces = APPLICATION_JSON_VALUE)
    public List<Customer> getCustomers() {
        return customerService.getList();
    }

    @RequestMapping(path = "/customers/{id}", produces = APPLICATION_JSON_VALUE)
    public Customer customer(@PathVariable("id") Long id) {

        Customer customer = customerService.getCustomerById(id);
        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException("!!!!!!!CUSTOMER NOT FOUND "+id+" !!!!!!!!!");
        }

        return customer;

    }

}


