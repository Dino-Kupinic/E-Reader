package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h4>Customer</h4>
 * <br>
 * Represents a customer which can borrow resources.
 *
 * @author Dino Kupinic
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  // TODO: Add relations
}
