package museum.domen;

import lombok.Data;
import src.model.TypeOfArtEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "likes")
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"user_id", "art_id", "type"})}
)
@Data
public class LikeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public UserModel user;

  @Column(name = "art_id")
  public Integer artId;

  @Enumerated(EnumType.STRING)
  public TypeOfArtEnum type;
}
