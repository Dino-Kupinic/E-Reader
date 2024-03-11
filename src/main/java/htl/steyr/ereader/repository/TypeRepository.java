package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
  /**
   * Find a type by its name
   *
   * @param name the name of the type
   * @return the type with the given name
   */
  Type findByName(String name);
}
