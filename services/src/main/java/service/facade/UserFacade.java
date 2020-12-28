package service.facade;

import service.model.ExistingUser;
import service.model.NewUser;

public interface UserFacade {

  ExistingUser getUser(NewUser user ) throws Exception;

  void updateUserPassword(String login, String password);
  ExistingUser createUser(NewUser user) throws Exception;
}
