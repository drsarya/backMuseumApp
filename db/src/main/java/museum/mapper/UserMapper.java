package museum.mapper;

import museum.domen.UserModel;
import org.springframework.data.repository.CrudRepository;
import src.model.RoleEnum;

public interface UserMapper extends CrudRepository<UserModel, Integer> {

  UserModel findByLogin(String login);

  UserModel findById(Integer id);

  UserModel findByLoginAndRole(String login, RoleEnum roleEnum);

}
