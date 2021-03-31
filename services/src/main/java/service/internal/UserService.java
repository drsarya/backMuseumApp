
package service.internal;

import service.model.OkModel;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;

public interface UserService {

  OkModel createUser(NewUser user) throws Exception;

  ExistingUser getUser(NewUser user) throws Exception;

  OkModel updateUserPassword(UserUpdate user) throws Exception;

  OkModel updateMuseumUserPass(UserMuseum user) throws Exception;
}
