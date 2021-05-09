package museum.repository;

import museum.domen.UserModel;
import org.springframework.data.repository.CrudRepository;
import src.model.RoleEnum;

public interface UserRepository extends CrudRepository<UserModel, Integer> {

  UserModel findByLogin(String login);

  UserModel findById(Integer id);

  UserModel findByLoginAndRole(String login, RoleEnum roleEnum);

}
