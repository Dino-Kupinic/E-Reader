package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h4>Resource Category</h4>
 * <br>
 * Represents a category that can belong to one or multiple resources.
 * A category example would be "Action", "Roman" or "Fantasy"
 *
 * @author Dino Kupinic
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  // TODO: Add relations
}
