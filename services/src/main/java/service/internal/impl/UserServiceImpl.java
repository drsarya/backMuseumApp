package service.internal.impl;

import museum.domen.MuseumModel;
import museum.repository.MuseumRepository;
import museum.repository.UserRepository;
import museum.domen.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ConfigEncrypt;
import service.internal.MuseumService;
import service.internal.UserService;
import service.mapper.UserMapper;
import service.model.AnswerModel;
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

  private final UserMapper userMapper;
  // private final MuseumRepository museumRepository;
  private final UserRepository userRepository;
  private final MuseumService museumService;

  @Autowired
  public UserServiceImpl(final UserMapper userMapper, final MuseumRepository museumRepository, final UserRepository userRepository, MuseumService museumService) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
    this.museumService = museumService;
  }

  @Override
  public AnswerModel createUser(final NewUser user) throws Exception {
    String newPassword = ConfigEncrypt.getSaltedHash(user.getPassword());
    ExistingMuseum museumModel = null;

    if (user.getMuseumId() != null) {
      museumModel = museumService.getMuseumById(user.getMuseumId());
    }
    if (museumModel != null && user.getRole() != RoleEnum.MUSEUM ||
      museumModel == null && user.getRole() == RoleEnum.MUSEUM) {
      throw new IllegalArgumentException(ValidationErrorTerms.INVALID_ROLE_OF_USER);
    }
    userRepository.save(userMapper.toUserModel(user, newPassword));
    return new AnswerModel("Успешная регистрация");
  }

  @Override
  public ExistingUser getUser(NewUser user) throws Exception {

    UserModel model = userRepository.findByLogin(user.getLogin());
    if (model == null) throw new IllegalArgumentException(ValidationErrorTerms.WRONG_DATA);
    if (model.getPassword() != null && ConfigEncrypt.check(user.getPassword(), model.getPassword()) && (model.getMuseum() == null || model.getMuseum().getState() == MuseumStateEnum.ACTIVE)) {
      return userMapper.toExistingUser(model);
    }
    return null;
  }


  @Override
  public AnswerModel updateUserPassword(UserUpdate user) throws Exception {

    UserModel model = userRepository.findById(user.getId());
    if (model == null) {
      throw new IllegalArgumentException(ValidationErrorTerms.WRONG_DATA);
    }
    String newPassword = ConfigEncrypt.getSaltedHash(user.getNewPassword());

    boolean pass = ConfigEncrypt.check(user.getPassword(), model.getPassword());
    if (pass) {
      model.setPassword(newPassword);
      userRepository.save(model);
      return new AnswerModel("Пароль успешно обновлен");
    } else {
      throw new IllegalArgumentException(ValidationErrorTerms.WRONG_DATA);
    }
  }

  @Override
  public AnswerModel updateMuseumUserPass(UserMuseum user) throws Exception {
    ExistingMuseum museumModel = museumService.getMuseumById(user.getIdCode());
    UserModel model = userRepository.findByLogin(user.getLogin());
    if (museumModel != null && model != null && museumModel.getState() == MuseumStateEnum.NOT_ACTIVE) {
      museumService.activateMuseum(museumModel.getId());
      String newPassword = ConfigEncrypt.getSaltedHash(user.getPassword());
      model.setPassword(newPassword);
      userRepository.save(model);
      return new AnswerModel("Успешная регистрация музея");
    }
    throw new IllegalArgumentException(ValidationErrorTerms.WRONG_DATA);
  }

  @Override
  public ExistingUser getById(Integer id) {
    return userMapper.toExistingUser(userRepository.findById(id));
  }
}
