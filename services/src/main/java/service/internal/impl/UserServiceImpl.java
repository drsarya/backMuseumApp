package service.internal.impl;

import museum.mapper.UserMapper;
import museum.domen.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ConfigEncrypt;
import service.internal.UserService;
import service.mapper.UserStruct;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserUpdate;

@Service
public class UserServiceImpl implements UserService {


  private final UserStruct userStruct;
  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(final UserStruct userStruct, final UserMapper userMapper) {
    this.userStruct = userStruct;
    this.userMapper = userMapper;

  }


  @Override
  public ExistingUser createUser(final NewUser user) throws Exception {
    String newPassword = ConfigEncrypt.getSaltedHash(user.getPassword());

    UserModel d = userMapper.save(userStruct.toUserModel(user, newPassword));
    return userStruct.toExistingUser(d);
  }

  @Override
  public ExistingUser getUser(NewUser user) throws Exception {

    UserModel model = userMapper.findByLoginAndRole(user.getLogin(), user.getRole());
    if (model.getPassword() != null && ConfigEncrypt.check(user.getPassword(), model.getPassword())) {
      return userStruct.toExistingUser(model);
    }
    return null;
  }


  @Override
  public Boolean updateUserPassword(UserUpdate user) throws Exception {

    UserModel model = userMapper.findByLoginAndRole(user.getLogin(),  user.getRole());
    if (model == null) {
      return false;
    }
    String newPassword = ConfigEncrypt.getSaltedHash(user.getNewPassword());
    if (model.getPassword() == null) {
       model.setPassword(newPassword);
      userMapper.save(model);
      return true;
    } else {
      boolean pass = ConfigEncrypt.check(user.getPassword(), model.getPassword());
      if (pass) {
         model.setPassword(newPassword);
        userMapper.save(model);
        return true;
      } else {
        return false;
      }
    }
  }
}
