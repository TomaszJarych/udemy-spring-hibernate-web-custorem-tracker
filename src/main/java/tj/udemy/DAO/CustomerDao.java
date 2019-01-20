package tj.udemy.DAO;

import org.springframework.stereotype.Component;
import tj.udemy.entity.Customer;

import java.util.List;

@Component
public interface CustomerDao {
    List<Customer> getList();

    void saveCustomer(Customer customer);

    Customer getCustomerById(Long id);

    void deleteCustomer(Customer customer);


}
