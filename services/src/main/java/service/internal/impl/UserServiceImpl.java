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
    String pass = ConfigEncrypt.getSaltedHash(user.getPassword());

    UserModel d = userMapper.createUser(userStruct.fromNewUser(user, pass));
    ExistingUser existingUser = userStruct.toExistingUser(d);
    return existingUser;
  }

  @Override
  public ExistingUser getUser(NewUser user) throws Exception  {
    UserModel model = userMapper.getUser(user.getLogin());

     if(model==null){
       return null;
     }
    Boolean pass = ConfigEncrypt.check(user.getPassword(), model.getPassword());
    if (pass) {
      return userStruct.toExistingUser(model);
    } else {
      return null;
    }

  }


  @Override
  public void updateUserPassword(String login, String password) {

  }
}
