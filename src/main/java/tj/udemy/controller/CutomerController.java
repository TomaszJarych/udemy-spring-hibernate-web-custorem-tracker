package tj.udemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tj.udemy.entity.Customer;
import tj.udemy.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CutomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/list")
    public String customerList(Model model) {

        List<Customer> list = service.getList();
        list.stream().forEach(System.out::println);
        model.addAttribute("list", list);

        return "customerList";
    }

    @GetMapping("/showForm")
    public String processForm(Model model) {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "customerForm";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(
            @ModelAttribute(name = "customer") Customer customer) {
        System.out.println("ID to:  " + customer.getId());
        service.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String processFormForUpdate(@RequestParam("customerId") Long id,
                                       Model model) {

        Customer customer = service.getCustomerById(id);
        model.addAttribute("customer", customer);

        return "customerForm";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") Long id) {
        Customer customer = service.getCustomerById(id);
        service.deleteCustomer(customer);

        return "redirect:/customer/list";
    }
}
