
package service.internal;

import service.model.ExistingUser;
import service.model.NewUser;
import service.model.UserUpdate;

public interface UserService {

  ExistingUser createUser(NewUser user) throws Exception;

  ExistingUser getUser(NewUser user ) throws Exception;

  Boolean updateUserPassword(UserUpdate user) throws Exception;

  ExistingUser getUserMuseum(String login);
}
