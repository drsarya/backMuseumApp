package service.facade;

import service.model.ExistingUser;
import service.model.NewUser;
import service.model.UserUpdate;

public interface UserFacade {

  ExistingUser getUser(NewUser user ) throws Exception;

  Boolean updateUserPassword(UserUpdate user) throws Exception;
  ExistingUser createUser(NewUser user) throws Exception;
 }
