package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.UserFacade;
import service.internal.UserService;
import service.model.ExistingUser;
import service.model.NewUser;

@Service
public class UserFacadeImpl implements UserFacade {

  private final UserService userService;

  public UserFacadeImpl(final UserService userService) {
    this.userService = userService;
  }

  @Override
  public ExistingUser createUser(final NewUser user) {
    return userService.createUser(user);
  }
}
