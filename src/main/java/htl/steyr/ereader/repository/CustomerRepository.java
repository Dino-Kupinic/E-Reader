package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  /**
   * Find a customer by its first name and last name
   * @param firstName the first name of the customer
   * @param lastName the last name of the customer
   * @return the customer with the given first name and last name
   */
  Customer findByFirstNameAndLastName(String firstName, String lastName);
}
