package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * <h4>Resource</h4>
 * <br>
 * Represents a resource which can be borrowed by a customer.
 *
 * @author Dino Kupinic
 */
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resource")
public class Resource {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  @NonNull
  private String name;

  @Column(name = "daily_rate", nullable = false)
  @NonNull
  private Double dailyRate;

  @Column(name = "is_deleted", nullable = false)
  @NonNull
  private Boolean isDeleted;

  @OneToMany(mappedBy = "resource")
  private List<Borrow> borrowList;

  @ManyToOne
  @JoinColumn(name = "category_id")
  @NonNull
  private Category category;

  @ManyToOne
  @JoinColumn(name = "type_id")
  @NonNull
  private Type type;

  @Override
  public String toString() {
    return getName();
  }
}
