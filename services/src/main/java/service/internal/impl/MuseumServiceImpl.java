package service.internal.impl;

import museum.domen.MuseumModel;
import museum.domen.UserModel;
import museum.mapper.MuseumMapper;
import museum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.MuseumService;
import service.mapper.MuseumStruct;
import service.model.AnswerModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.museum.UpdatableMuseumAdmin;
import src.model.MuseumStateEnum;
import src.model.RoleEnum;
import validation.ValidationErrorTerms;

import java.util.ArrayList;
import java.util.List;

@Service
public class MuseumServiceImpl implements MuseumService {
  private final UserMapper userMapper;
  private final MuseumMapper museumMapper;
  private final MuseumStruct museumStruct;

  @Autowired
  public MuseumServiceImpl(MuseumMapper museumMapper, MuseumStruct museumStruct, UserMapper userMapper) {
    this.museumMapper = museumMapper;
    this.museumStruct = museumStruct;
    this.userMapper = userMapper;
  }


  @Override
  public AnswerModel getOwnerByMuseumId(Integer id) {
    MuseumModel museumModel = museumMapper.findById(id);
    if (museumModel != null) {
      return new AnswerModel(museumModel.getWorker().getLogin());
    }
    throw new IllegalArgumentException(ValidationErrorTerms.OWNER_NOT_FOUND);
  }

  @Override
  public ExistingMuseum getMuseumById(Integer id) {
    MuseumModel museumModel = museumMapper.findById(id);
    if (museumModel != null) {
      return museumStruct.toExistingMuseum(museumModel);
    }
    throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
  }

  @Override
  public AnswerModel lockMuseum(Integer id) {
    MuseumModel museumModel = museumMapper.findById(id);
    String result = "";
    if (museumModel != null) {
      switch (museumModel.getState()) {
        case ACTIVE:
          museumModel.setState(MuseumStateEnum.BLOCKED);
          museumMapper.save(museumModel);
          result = "Музей заблокирован";
          break;
        case BLOCKED:
          museumModel.setState(MuseumStateEnum.ACTIVE);
          museumMapper.save(museumModel);
          result = "Музей разблокирован";
          break;
        case NOT_ACTIVE:
          throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_ACTIVATED);
      }
      return new AnswerModel(result);
    }
    throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
  }


  @Override
  public AnswerModel deleteMuseum(Integer id) {
    MuseumModel museumModel = museumMapper.findById(id);
    if (museumModel != null) {
      if (museumModel.getState() == MuseumStateEnum.BLOCKED || museumModel.getState() == MuseumStateEnum.ACTIVE)
        throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_CANT_BE_DELETED);
      museumMapper.delete(museumModel);
      return new AnswerModel("Музей удалён");
    }
    throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
  }

  @Override
  public List<ExistingMuseum> getAllMuseums() {
    Iterable<MuseumModel> museumModels = museumMapper.findAll();

    List<MuseumModel> actualList = new ArrayList<MuseumModel>();
    museumModels.forEach(actualList::add);
    return museumStruct.toListExistingMuseum(actualList);
  }


  @Override
  public AnswerModel createMuseum(BaseMuseum baseMuseum, String login) {
    UserModel u = userMapper.findByLogin(login);
    if (u != null)
      throw new IllegalArgumentException(ValidationErrorTerms.KEY_NOT_UNIQUE);

    MuseumModel museumModel = museumMapper.save(museumStruct.toMuseumModel(baseMuseum));
    UserModel userModel = new UserModel();
    userModel.setLogin(login);
    userModel.setMuseum(museumModel);
    userModel.setRole(RoleEnum.MUSEUM);
    userMapper.save(userModel);
    return new AnswerModel("Успешное создание музея");
  }

  @Override
  public AnswerModel updateMuseumInfo(UpdatableMuseum updatableMuseum) {

    MuseumModel m = museumMapper.findById(updatableMuseum.getId());
    if (m == null)
      throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);

    if (updatableMuseum.getDescription() != null && !updatableMuseum.getDescription().trim().isEmpty()) {
      m.setDescription(updatableMuseum.getDescription());
    }
    if (updatableMuseum.getImageUrl() != null && !updatableMuseum.getImageUrl().trim().isEmpty()) {
      m.setImage(updatableMuseum.getImageUrl());
    }
    museumMapper.save(m);
    return new AnswerModel("Успешное обновление данных");
  }

  @Override
  public AnswerModel updateMuseumByAdmin(UpdatableMuseumAdmin updatableMuseum) {

    MuseumModel m = museumMapper.findById(updatableMuseum.getId());
    if (m == null)
      throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
    if (updatableMuseum.getNameMuseum() != null && !updatableMuseum.getNameMuseum().trim().isEmpty()) {
      m.setNameMuseum(updatableMuseum.getNameMuseum());
    }
    if (updatableMuseum.getAddress() != null && !updatableMuseum.getAddress().trim().isEmpty()) {
      m.setAddress(updatableMuseum.getAddress());
    }
    museumMapper.save(m);
    return new AnswerModel("Успешное обновление данных");
  }
}
