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
  public ExistingUser getUser(NewUser user) throws Exception  {
    return userService.getUser(user);
  }




  @Override
  public void updateUserPassword(String login, String password) {

  }

  @Override
  public ExistingUser createUser(final NewUser user) throws Exception {
    return userService.createUser(user);
  }



}
