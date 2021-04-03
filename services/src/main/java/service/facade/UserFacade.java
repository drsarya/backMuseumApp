package service.facade;

import org.springframework.validation.annotation.Validated;
import service.model.OkModel;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;
@Validated
public interface UserFacade {

  ExistingUser getUser(NewUser user ) throws Exception;
  OkModel updateUserPassword(UserUpdate user) throws Exception;
  OkModel createUser(NewUser user) throws Exception;
  OkModel updateMuseumUserPass(UserMuseum user) throws Exception;

}
