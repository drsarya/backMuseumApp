
package service.internal;

import service.model.ExistingUser;
import service.model.NewUser;

public interface UserService {

  ExistingUser createUser(NewUser user) throws Exception;

  ExistingUser getUser(NewUser user ) throws Exception;



  void updateUserPassword(String login, String password);
}
