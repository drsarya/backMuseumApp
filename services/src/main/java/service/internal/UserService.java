
package service.internal;

import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;

public interface UserService {

  ExistingUser createUser(NewUser user) throws Exception;

  ExistingUser getUser(NewUser user ) throws Exception;

  Boolean updateUserPassword(UserUpdate user) throws Exception;
  Boolean updateMuseumUserPass(UserMuseum user) throws Exception;
 }
