package museum.domen;

import src.model.TypeOfArtEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "likes")
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"user_id", "art_id", "type"})}
)
public class LikeModel implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public UserModel user;

  @Column(name = "art_id")
  public Integer artId;

  public TypeOfArtEnum type;


}
