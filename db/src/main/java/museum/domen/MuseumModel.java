package museum.domen;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
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

  private Long id;
  //  @Column(name = "museum_login")
//  private String login;
  @Column(name = "name_museum")
  private String nameMuseum;
  @Column(name = "address")
  private String address;
  @Nullable
  private String description;
  @Nullable
  private String image;

  private Boolean isActive = false;
  @OneToMany(mappedBy = "museum", cascade = CascadeType.ALL)
  private List<UserModel> workers;
  @OneToMany(mappedBy = "museum")
  private List<ExhibitionModel> exhibitionModels;
}
