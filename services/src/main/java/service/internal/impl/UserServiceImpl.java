package service.internal.impl;


import dataBase.mapper.UserMapper;
import dataBase.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.UserService;
import service.mapper.UserStruct;
import service.model.ExistingUser;
import service.model.NewUser;

@Service
public class UserServiceImpl implements UserService {


   private final UserMapper userMapper;
  private final UserStruct userStruct;

  @Autowired
  public UserServiceImpl(final UserStruct userStruct, final UserMapper userdMapper ) {
    this.userStruct = userStruct;
    this.userMapper = userdMapper;
  }


  @Override
  public ExistingUser createUser(final NewUser user) {
    // Выделить ID для пользователя
    //long newId = lastId.addAndGet(1);


    // Преобразовать NewUser в ExistingUser
   // ExistingUser existingUser = userMapperConverter.fromNewUser(user, 1);
    UserModel model = userStruct.fromNewUser(user);
    ExistingUser existingUser = userStruct.toExistingUser(userMapper.createUser(model));
    // Сохранить в HashMap
    //USERS.put(newId, existingUser);

    // Вернуть ExistingUser клиенту
    return existingUser;
  }
}
