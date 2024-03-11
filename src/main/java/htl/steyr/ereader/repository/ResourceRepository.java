package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Category;
import htl.steyr.ereader.model.Resource;
import htl.steyr.ereader.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
  /**
   * Find a resource by its name, category, type and daily rate
   *
   * @param name      the name of the resource
   * @param category  the category of the resource
   * @param type      the type of the resource
   * @param dailyRate the daily rate of the resource
   * @return the resource with the given name, category, type and daily rate
   */
  Resource findByNameAndCategoryAndTypeAndDailyRate(String name, Category category, Type type, double dailyRate);

  /**
   * Find all resources that are not deleted
   *
   * @return a list of all resources that are not deleted
   */
  List<Resource> findAllByIsDeletedFalse();
}
