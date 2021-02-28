package museum.mapper;

import museum.domen.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import src.model.UsersRole;

import javax.management.relation.Role;

@Component
public interface UserMapper extends CrudRepository<UserModel, Long> {

  UserModel findByLogin(String login);

  UserModel findByLoginAndRole(String login, UsersRole usersRole);
//  @Query("" +
//    "SELECT  id,   type,    login,   password  " +
//    "FROM user   " +
//    "WHERE login = #{login} AND password IS NULL  "
//  )
//  UserModel getUserMuseum(@Param("login") String login);
}
