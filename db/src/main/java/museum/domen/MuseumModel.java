package museum.domen;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "museums")
@Table(uniqueConstraints = {

  @UniqueConstraint(columnNames = {"name_museum", "address"})
}
)
@Data
public class MuseumModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "museum_login")
  private String login;
  @Column(name = "name_museum")
  private String nameMuseum;
  @Column(name = "address")
  private String address;
  private String description;
  private byte[] image;
//    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "museum_id")
//  private List<ExhibitionModel> exhibitionLists;
}
