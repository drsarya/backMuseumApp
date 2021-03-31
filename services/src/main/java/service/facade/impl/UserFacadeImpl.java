package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.UserFacade;
import service.internal.UserService;
import service.model.OkModel;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;

@Service
public class UserFacadeImpl implements UserFacade {

  private final UserService userService;

  public UserFacadeImpl(final UserService userService) {
    this.userService = userService;
  }

  @Override
  public ExistingUser getUser(NewUser user) throws Exception {
    return userService.getUser(user);
  }

  @Override
  public OkModel updateUserPassword(UserUpdate user) throws Exception {
    return userService.updateUserPassword(user);
  }

  @Override
  public OkModel createUser(final NewUser user) throws Exception {
    return userService.createUser(user);
  }

  @Override
  public OkModel updateMuseumUserPass(UserMuseum user) throws Exception {
    return userService.updateUserPassword(user);
  }


}
