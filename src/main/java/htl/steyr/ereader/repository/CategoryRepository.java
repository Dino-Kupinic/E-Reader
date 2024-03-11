package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  /**
   * Find a category by its name
   * @param name the name of the category
   * @return the category with the given name
   */
  Category findByName(String name);
}
