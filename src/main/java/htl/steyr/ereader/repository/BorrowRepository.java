package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Borrow;
import htl.steyr.ereader.model.Customer;
import htl.steyr.ereader.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
  /**
   * Find all borrows of a customer
   *
   * @param resource the resource
   * @return a list of all borrows of the customer
   */
  List<Borrow> findByResource(Resource resource);

  /**
   * Find all borrows of a customer
   *
   * @param customer   the customer
   * @param isReturned the isReturned flag
   * @return a list of all borrows of the customer
   */
  List<Borrow> findByCustomerAndIsReturned(Customer customer, Boolean isReturned);
}
