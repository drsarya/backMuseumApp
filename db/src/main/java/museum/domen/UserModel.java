package museum.domen;

import lombok.*;
import src.model.UsersRole;

import javax.persistence.*;

@Entity(name = "users" )
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = "login", name = "uniqueNameConstraint")}
)
@Data
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "login")
  private String login;
  @Enumerated(EnumType.STRING)
  private UsersRole role;
  private String password;


}
