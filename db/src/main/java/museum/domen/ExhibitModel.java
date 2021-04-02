package museum.domen;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "exhibits")
@Data
public class ExhibitModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id")
  private AuthorModel author;

  private String name;

  private String imageUrl;

  private String description;

  private String dateOfCreate;
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "exhibition_id")
  private ExhibitionModel exhibition;

}
