package dataBase.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
  "login",
  "password",
  "type"
})
public class UserModel {
  public Integer id;

  public String login;
  public String password;
  //true - admin
  //false обычный пользователь

  public boolean type;
}
