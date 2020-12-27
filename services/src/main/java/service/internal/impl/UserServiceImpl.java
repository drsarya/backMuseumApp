package service.internal.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.UserService;
import service.mapper.UserMapper;
import service.model.ExistingUser;
import service.model.NewUser;

@Service
public class UserServiceImpl implements UserService {

  private final Map<Long, ExistingUser> USERS = new ConcurrentHashMap<>();

  private final AtomicLong lastId = new AtomicLong(0);

  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(final UserMapper userMapper) {
    this.userMapper = userMapper;
  }


  @Override
  public ExistingUser createUser(final NewUser user) {
    // Выделить ID для пользователя
    //long newId = lastId.addAndGet(1);

    // Преобразовать NewUser в ExistingUser
    ExistingUser existingUser = userMapper.fromNewUser(user, 1);

    // Сохранить в HashMap
    //USERS.put(newId, existingUser);

    // Вернуть ExistingUser клиенту
    return existingUser;
  }
}
