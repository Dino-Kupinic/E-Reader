package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@Entity
@Table(name = "resource")
public class Resource {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Borrow> borrowList;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private Type type;
}
