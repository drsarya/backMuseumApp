package museum.mapper;

import museum.domen.UserModel;
import org.springframework.data.repository.CrudRepository;
import src.model.RoleEnum;


public interface UserMapper extends CrudRepository<UserModel, Long> {

  UserModel findByLogin(String login);

  UserModel findByLoginAndRole(String login, RoleEnum roleEnum);
//  @Query("" +
//    "SELECT  id,   type,    login,   password  " +
//    "FROM user   " +
//    "WHERE login = #{login} AND password IS NULL  "
//  )
//  UserModel getUserMuseum(@Param("login") String login);
}
