package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  @NonNull
  private String name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Resource> resourceList;
}
