package service.internal.impl;

import museum.app.mapper.UserMapper;
import museum.app.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ConfigEncrypt;
import service.internal.UserService;
import service.mapper.UserStruct;
import service.model.ExistingUser;
import service.model.NewUser;
import service.model.UserUpdate;

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
    UserModel d;
    if (user.getPassword() != null) {
      String pass = ConfigEncrypt.getSaltedHash(user.getPassword());
      d = userMapper.createUser(userStruct.toUserModel(user, pass));

    } else {
      d = userMapper.createUser(userStruct.toUserModel(user, null));
    }

    ExistingUser existingUser = userStruct.toExistingUser(d);
    return existingUser;
  }

  @Override
  public ExistingUser getUser(NewUser user) throws Exception {
    UserModel model = userMapper.getUser(user.getLogin());

    if (model == null) {
      return null;
    }
    if(model.getPassword()==null){
      return null;

    }else{
      Boolean pass = ConfigEncrypt.check(user.getPassword(), model.getPassword());
      if (pass) {
        return userStruct.toExistingUser(model);
      } else {
        return null;
      }
    }


  }


  @Override
  public Boolean updateUserPassword(UserUpdate user) throws Exception {

    UserModel model = userMapper.getUser(user.getLogin());

    if (model == null) {
      return false;
    }
    if (model.getPassword() == null) {
      String newPassword = ConfigEncrypt.getSaltedHash(user.getNewPassword());

      UserModel usermodel = userStruct.toUserModel(user, newPassword);
      userMapper.updatePassword(usermodel);
      return true;

    } else {
      Boolean pass = ConfigEncrypt.check(user.getPassword(), model.getPassword());
      if (pass) {
        String newPassword = ConfigEncrypt.getSaltedHash(user.getNewPassword());

        UserModel usermodel = userStruct.toUserModel(user, newPassword);
        userMapper.updatePassword(usermodel);
        return true;
      } else {
        return false;
      }
    }


  }

  @Override
  public ExistingUser getUserMuseum(String login) {
    UserModel usermodel = userMapper.getUserMuseum(login);
    if (usermodel == null) {
      return null;
    } else {
      return userStruct.toExistingUser(usermodel);
    }

  }
}
