package tj.udemy.DAO;

import org.springframework.stereotype.Component;
import tj.udemy.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class CustomerDAOImp implements CustomerDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Customer> getList() {
        Query query = entityManager
                .createQuery("SELECT c FROM Customer c ORDER BY lastName");
        List<Customer> list = query.getResultList();
        return list;
    }

    @Override
    public void saveCustomer(Customer customer) {
        if (customer.getId() == null) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
        // w zasadzie to powinno być w klasie CustomerService

    }

    @Override
    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        entityManager.remove((entityManager.contains(customer))
                ? customer
                : entityManager.merge(customer));
    }

}
