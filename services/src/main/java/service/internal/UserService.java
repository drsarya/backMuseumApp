
package service.internal;

import service.model.ExistingUser;
import service.model.NewUser;

public interface UserService {

  ExistingUser createUser(NewUser user);
}
