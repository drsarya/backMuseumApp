package museum.app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
  "password",
  "login",
  "type"
})
public class UserModel {

  private long id;

  private String login;
  private Boolean type;

  private String password;


}
