package museum.domen;

import com.sun.istack.Nullable;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import src.model.MuseumStateEnum;
import validation.ValidationErrorTerms;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity(name = "museums")
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name_museum", "address"})}
)
@Data
public class MuseumModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name_museum", nullable = false)
  @NotEmpty(message = ValidationErrorTerms.NAME_MOST_BE_SET)
  private String nameMuseum;

  @Column(name = "address", nullable = false)
  @NotEmpty(message = ValidationErrorTerms.ADDRESS_MOST_BE_SET)
  private String address;

  @Nullable
  @Column(length=1500)
  private String description;

  @Nullable
  @Column(length=1500)
  private String image;

  @Enumerated(EnumType.STRING)
  private MuseumStateEnum state = MuseumStateEnum.NOT_ACTIVE;

  @OneToOne(mappedBy = "museum", cascade = CascadeType.REMOVE)
  private UserModel worker;

  @OneToMany(mappedBy = "museum", orphanRemoval = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<ExhibitionModel> exhibitionModels;
}
