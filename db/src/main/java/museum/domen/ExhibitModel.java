package museum.domen;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "exhibits")
@Data
public class ExhibitModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @OneToOne
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  @JoinColumn(name = "author_id")
  private AuthorModel author;

  private String name;

  private String imageUrl;

  private String description;

  private String dateOfCreate;
  @ManyToOne
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  @JoinColumn(name = "exhibition_id")
  private ExhibitionModel exhibition;

}
