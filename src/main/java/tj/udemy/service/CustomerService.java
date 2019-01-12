package tj.udemy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tj.udemy.DAO.CustomerDao;
import tj.udemy.entity.Customer;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDao dao;

    public List<Customer> getList() {

        return dao.getList();
    }


    public void saveCustomer(Customer customer) {
        dao.saveCustomer(customer);
    }


    public Customer getCustomerById(Long id) {
        return dao.getCustomerById(id);
    }

    public void deleteCustomer(Customer customer) {
        dao.deleteCustomer(customer);
    }
}
