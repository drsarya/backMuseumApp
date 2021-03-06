package museum.domen;

import src.model.TypeOfArtEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "likes")
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"user_id", "exhbtId"})}
)
public class LikeModel implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer id;

  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  public UserModel user;

  public Integer exhbtId;


  public TypeOfArtEnum type;
}
