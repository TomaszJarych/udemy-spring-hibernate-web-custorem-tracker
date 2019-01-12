package tj.udemy.DAO;

import org.springframework.stereotype.Component;
import tj.udemy.entity.Customer;

import java.util.List;

@Component
public interface CustomerDao {
    public List<Customer> getList();

    public void saveCustomer(Customer customer);

    public Customer getCustomerById(Long id);

    public void deleteCustomer(Customer customer);


}
