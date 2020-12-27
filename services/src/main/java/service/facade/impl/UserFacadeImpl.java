package service.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.facade.UserFacade;
import service.internal.UserService;
import service.meta.Facade;
import service.model.ExistingUser;
import service.model.NewUser;

@Facade
public class UserFacadeImpl implements UserFacade {

  private final UserService userService;

  public UserFacadeImpl(final UserService userService) {
    this.userService = userService;
  }

  @Override
  @Transactional

  public ExistingUser createUser(final NewUser user) {
    return userService.createUser(user);
  }
}
