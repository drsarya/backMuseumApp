package museum.domen;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import src.model.MuseumStateEnum;
import validation.ValidationErrorTerms;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

  @Column(name = "name_museum", nullable = false)
 // @NotEmpty(message = ValidationErrorTerms.NAME_MOST_BE_SET)
  private String nameMuseum;

  @Column(name = "address", nullable = false)
 // @NotEmpty(message = ValidationErrorTerms.ADDRESS_MOST_BE_SET)
  private String address;
  @Nullable
  private String description;
  @Nullable
  private String image;
  @Enumerated(EnumType.STRING)
  private MuseumStateEnum state = MuseumStateEnum.NOT_ACTIVE;
  @OneToOne(mappedBy = "museum", cascade = CascadeType.ALL)
  private UserModel worker;
  @OneToMany(mappedBy = "museum")
  private List<ExhibitionModel> exhibitionModels;
}
