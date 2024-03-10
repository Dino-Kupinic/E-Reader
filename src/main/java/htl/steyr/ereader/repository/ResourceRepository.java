package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Category;
import htl.steyr.ereader.model.Resource;
import htl.steyr.ereader.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
  Resource findByNameAndCategoryAndTypeAndDailyRate(String name, Category category, Type type, double dailyRate);
}
