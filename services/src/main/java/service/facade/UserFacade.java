package service.facade;

import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserUpdate;

public interface UserFacade {

  ExistingUser getUser(NewUser user ) throws Exception;
  Boolean updateUserPassword(UserUpdate user) throws Exception;
  ExistingUser createUser(NewUser user) throws Exception;
 }
