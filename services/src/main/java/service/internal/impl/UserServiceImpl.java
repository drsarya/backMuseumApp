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
import service.model.OkModel;
import service.model.museum.ExistingMuseum;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;
import validation.ValidationErrorTerms;
import src.model.MuseumStateEnum;
import src.model.RoleEnum;

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
  public OkModel createUser(final NewUser user) throws Exception {
    String newPassword = ConfigEncrypt.getSaltedHash(user.getPassword());
    MuseumModel museumModel = null;

    if (user.getMuseumId() != null) {
      museumModel = museumMapper.findById( user.getMuseumId());
    }
    if (museumModel != null && user.getRole() != RoleEnum.MUSEUM ||
      museumModel == null && user.getRole() == RoleEnum.MUSEUM) {
      throw new IllegalArgumentException(ValidationErrorTerms.INVALID_ROLE_OF_USER);
    }

    UserModel d = userMapper.save(userStruct.toUserModel(user, newPassword, museumModel));
    ExistingMuseum existingMuseum = museumStruct.toExistingMuseum(d.getMuseum());

    return new OkModel("Успешная регистрация");
  }

  @Override
  public ExistingUser getUser(NewUser user) throws Exception {


    UserModel model = userMapper.findByLogin(user.getLogin());
    if (model == null) throw new IllegalArgumentException("Ошибка входа");
    if (model.getPassword() != null && ConfigEncrypt.check(user.getPassword(), model.getPassword()) && model.getMuseum().getState() == MuseumStateEnum.ACTIVE) {
      ExistingMuseum existingMuseum = null;
      if (model.getMuseum() != null) {
        existingMuseum = museumStruct.toExistingMuseum(model.getMuseum());
      }
      return userStruct.toExistingUser(model, existingMuseum);
    }
    return null;
  }


  @Override
  public OkModel updateUserPassword(UserUpdate user) throws Exception {

    UserModel model = userMapper.findByLoginAndRole(user.getLogin(), user.getRole());
    if (model == null) {
      throw new IllegalArgumentException("Ошибка обновления");

    }
    String newPassword = ConfigEncrypt.getSaltedHash(user.getNewPassword());

    boolean pass = ConfigEncrypt.check(user.getPassword(), model.getPassword());
    if (pass) {
      model.setPassword(newPassword);
      userMapper.save(model);
      return new OkModel("Пароль успешно обновлен");
    } else {
      throw new IllegalArgumentException("Неверный пароль");
    }

  }

  @Override
  public OkModel updateMuseumUserPass(UserMuseum user) throws Exception {
    MuseumModel museumModel = museumMapper.findById(  user.getIdCode());
    UserModel model = userMapper.findByLogin(user.getLogin());
    if (museumModel != null && model != null && museumModel.getState() == MuseumStateEnum.NOT_ACTIVE) {
      museumModel.setState(MuseumStateEnum.ACTIVE);
      museumMapper.save(museumModel);
      String newPassword = ConfigEncrypt.getSaltedHash(user.getPassword());
      model.setPassword(newPassword);
      userMapper.save(model);
      return new OkModel("Успешная регистрация музея");
    }
    throw new IllegalArgumentException("Неверные данные");
  }
}
