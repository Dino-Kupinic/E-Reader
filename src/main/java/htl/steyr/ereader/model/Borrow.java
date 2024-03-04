package htl.steyr.ereader.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * <h4>Borrow</h4>
 * <br>
 * Represents a borrow that includes the start and end date.
 * A customer can have multiple borrows.
 *
 * @author Dino Kupinic
 */
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrow")
public class Borrow {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "start_date", nullable = false)
  @NonNull
  private Date startDate;

  @Column(name = "end_date", nullable = false)
  @NonNull
  private Date endDate;

  @Column(name = "is_returned", nullable = false)
  @NonNull
  private Boolean isReturned;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "resource_id")
  private Resource resource;
}
