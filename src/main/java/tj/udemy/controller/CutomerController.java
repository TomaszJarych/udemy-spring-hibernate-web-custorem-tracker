package tj.udemy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tj.udemy.DAO.CustomerDAOImp;
import tj.udemy.entity.Customer;
import tj.udemy.service.CustomerService;

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
		System.out.println("ID to:  "+customer.getId());
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
