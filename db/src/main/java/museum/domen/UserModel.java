package museum.domen;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String login;
  private Boolean type;
  private String password;


}
