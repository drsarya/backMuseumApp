package service.internal.impl;

import museum.domen.MuseumModel;
import museum.mapper.MuseumMapper;
import museum.mapper.UserMapper;
import museum.domen.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ConfigEncrypt;
import service.internal.UserService;
import service.mapper.MuseumStruct;
import service.mapper.UserStruct;
import service.model.museum.ExistingMuseum;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;
import service.validation.ValidationErrorTerms;
import src.model.RoleEnum;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {


  private final UserStruct userStruct;
  private final MuseumStruct museumStruct;
  private final MuseumMapper museumMapper;
  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(final UserStruct userStruct, final MuseumMapper museumMapper, final MuseumStruct museumStruct, final UserMapper userMapper) {
    this.userStruct = userStruct;
    this.userMapper = userMapper;
    this.museumStruct = museumStruct;
    this.museumMapper = museumMapper;
  }


  @Override
  public ExistingUser createUser(final NewUser user) throws Exception {
    String newPassword = ConfigEncrypt.getSaltedHash(user.getPassword());
    MuseumModel museumModel = null;

    if (user.getMuseumId() != null) {
      museumModel = museumMapper.findById((long) user.getMuseumId());
    }
    if (museumModel != null && user.getRole() != RoleEnum.MUSEUM ||
      museumModel == null && user.getRole() == RoleEnum.MUSEUM) {
      throw new IllegalArgumentException(ValidationErrorTerms.INVALID_ROLE_OF_USER);
    }
    UserModel d = userMapper.save(userStruct.toUserModel(user, newPassword, museumModel));
    ExistingMuseum existingMuseum = museumStruct.toExistingMuseum(d.getMuseum());

    return userStruct.toExistingUser(d, existingMuseum);
  }

  @Override
  public ExistingUser getUser(NewUser user) throws Exception {

    UserModel model = userMapper.findByLogin(user.getLogin());
    if(model == null) throw new IllegalArgumentException("Ошибка входа");
    if (model.getPassword() != null && ConfigEncrypt.check(user.getPassword(), model.getPassword())) {
      ExistingMuseum existingMuseum = null;
      if (model.getMuseum() != null) {
        existingMuseum = museumStruct.toExistingMuseum(model.getMuseum());
      }
      return userStruct.toExistingUser(model, existingMuseum);
    }
    return null;
  }


  @Override
  public Boolean updateUserPassword(UserUpdate user) throws Exception {

    UserModel model = userMapper.findByLoginAndRole(user.getLogin(), user.getRole());
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

  @Override
  public Boolean updateMuseumUserPass(UserMuseum user) throws Exception {
    if (museumMapper.findById((long) user.getIdCode()) != null && Objects.requireNonNull(user.getMuseumId()).equals(user.getIdCode())) {
      String newPassword = ConfigEncrypt.getSaltedHash(user.getNewPassword());
      UserModel model = userMapper.findByLoginAndRole(user.getLogin(), user.getRole());
      model.setPassword(newPassword);
      userMapper.save(model);
      return true;
    }
    return false;
  }
}
