package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Borrow;
import htl.steyr.ereader.model.Customer;
import htl.steyr.ereader.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
  List<Borrow> findByResource(Resource resource);
  List<Borrow> findByCustomerAndIsReturned(Customer customer, Boolean isReturned);
}
