package museum.domen;

import com.sun.istack.Nullable;
import lombok.*;
import src.model.RoleEnum;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames ={ "login", "role"})}
)
@Data
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "login")
  private String login;
  @Enumerated(EnumType.STRING)
  private RoleEnum role;
  private String password;

  @ManyToOne
  @JoinColumn(name = "museum_id")
  private MuseumModel museum;

  @OneToMany(mappedBy="user")
   public List<LikeModel> likes;
}
