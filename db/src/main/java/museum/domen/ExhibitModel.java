package museum.domen;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "exhibits")
@Data
public class ExhibitModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer id;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id")
  public AuthorModel author;

  public String name;

  public String imageUrl;

  public String description;

  public String dateOfCreate;
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "exhibition_id")
  public ExhibitionModel exhibition;

}
