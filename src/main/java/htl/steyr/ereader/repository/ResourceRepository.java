package htl.steyr.ereader.repository;

import htl.steyr.ereader.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
