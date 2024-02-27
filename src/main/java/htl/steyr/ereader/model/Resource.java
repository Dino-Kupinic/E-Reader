package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h4>Resource</h4>
 * <br>
 * Represents a resource which can be borrowed by a customer.
 *
 * @author Dino Kupinic
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "resource")
public class Resource {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  // TODO: Add relations
}
