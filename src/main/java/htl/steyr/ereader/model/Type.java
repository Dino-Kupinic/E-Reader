package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h4>Resource Type</h4>
 * <br>
 * Represents a type that can belong to one or multiple resources.
 * It describes what kind of medium the associated resource is.
 * A type example would be "Book", "DVD" or "Newspaper"
 *
 * @author Dino Kupinic
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "Type")
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  // TODO: Add relations
}
