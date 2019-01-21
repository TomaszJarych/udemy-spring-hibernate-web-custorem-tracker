package tj.udemy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tj.udemy.commons.errorhandling.CustomerNotFoundException;
import tj.udemy.entity.Customer;
import tj.udemy.service.CustomerService;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers", produces = APPLICATION_JSON_VALUE)
    public List<Customer> getCustomers() {
        return customerService.getList();
    }

    @GetMapping(path = "/customers/{id}", produces = APPLICATION_JSON_VALUE)
    public Customer customer(@PathVariable("id") Long id) {

        Customer customer = customerService.getCustomerById(id);
        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException("!!!!!!!CUSTOMER NOT FOUND " + id + " !!!!!!!!!");
        }

        return customer;

    }

    @PostMapping(path = "/customers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Customer saveNewCustomer(@RequestBody Customer customer) {

        customer.setId(null);
        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping(path = "/customers", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Customer updateCustomer(@RequestBody Customer customer) {

        customerService.saveCustomer(customer);

        return customer;

    }

    @DeleteMapping(path = "/customers/{id}")
    public String deleteCustomer(@PathVariable("id")Long id){

        Customer tempCustomer = customerService.getCustomerById(id);
        if (Objects.isNull(tempCustomer)){
            throw new CustomerNotFoundException("Customer id not found: " +id);
        }
        customerService.deleteCustomer(tempCustomer);

        return "Deleted customer id: "+ id;
    }


}


