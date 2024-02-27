package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
