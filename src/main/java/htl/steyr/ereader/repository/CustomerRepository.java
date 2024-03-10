package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findByFirstNameAndLastName(String firstName, String lastName);
}
