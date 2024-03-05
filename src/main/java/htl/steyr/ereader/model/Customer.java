package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * <h4>Customer</h4>
 * <br>
 * Represents a customer which can borrow resources.
 *
 * @author Dino Kupinic
 */
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false)
  @NonNull
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @NonNull
  private String lastName;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Borrow> borrowList;

  @Override
  public String toString() {
    return getFirstName() + " " + getLastName();
  }
}
