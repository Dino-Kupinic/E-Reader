package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
