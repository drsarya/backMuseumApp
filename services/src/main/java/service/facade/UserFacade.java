package service.facade;

import service.model.ExistingUser;
import service.model.NewUser;

public interface UserFacade {

  ExistingUser createUser(NewUser user);
}
