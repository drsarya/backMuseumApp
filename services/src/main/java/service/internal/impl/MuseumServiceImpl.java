package service.internal.impl;

import museum.domen.MuseumModel;
import museum.domen.UserModel;
import museum.repository.MuseumRepository;
import museum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.FileLoaderService;
import service.internal.MuseumService;
import service.mapper.MuseumMapper;
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
  private final UserRepository userRepository;
  private final MuseumRepository museumRepository;
  private final MuseumMapper museumMapper;
  private final FileLoaderService fileLoaderService;

  @Autowired
  public MuseumServiceImpl(MuseumRepository museumRepository, MuseumMapper museumMapper, UserRepository userRepository, FileLoaderService fileLoaderService) {
    this.museumRepository = museumRepository;
    this.museumMapper = museumMapper;
    this.userRepository = userRepository;
    this.fileLoaderService = fileLoaderService;
  }


  @Override
  public AnswerModel getOwnerByMuseumId(Integer id) {
    MuseumModel museumModel = museumRepository.findById(id);
    if (museumModel != null) {
      return new AnswerModel(museumModel.getWorker().getLogin());
    }
    throw new IllegalArgumentException(ValidationErrorTerms.OWNER_NOT_FOUND);
  }

  @Override
  public ExistingMuseum getMuseumById(Integer id) {
    MuseumModel museumModel = museumRepository.findById(id);
    if (museumModel != null) {
      return museumMapper.toExistingMuseum(museumModel);
    }
    throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
  }

  @Override
  public AnswerModel lockMuseum(Integer id) {
    MuseumModel museumModel = museumRepository.findById(id);
    String result = "";
    if (museumModel != null) {
      switch (museumModel.getState()) {
        case ACTIVE:
          museumModel.setState(MuseumStateEnum.BLOCKED);
          museumRepository.save(museumModel);
          result = "Музей заблокирован";
          break;
        case BLOCKED:
          museumModel.setState(MuseumStateEnum.ACTIVE);
          museumRepository.save(museumModel);
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
    MuseumModel museumModel = museumRepository.findById(id);
    if (museumModel != null) {
      if (museumModel.getState() == MuseumStateEnum.BLOCKED || museumModel.getState() == MuseumStateEnum.ACTIVE)
        throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_CANT_BE_DELETED);
      museumRepository.delete(museumModel);
      return new AnswerModel("Музей удалён");
    }
    throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
  }

  @Override
  public ExistingMuseum activateMuseum(Integer id) {
    MuseumModel museumModel = museumRepository.findById(id);
    if(museumModel!=null){
      museumModel.setState(MuseumStateEnum.ACTIVE);
      museumRepository.save(museumModel);
    }else { throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);}

    return museumMapper.toExistingMuseum(museumModel);
  }

  @Override
  public List<ExistingMuseum> getAllMuseums() {
    Iterable<MuseumModel> museumModels = museumRepository.findAll();

    List<MuseumModel> actualList = new ArrayList<MuseumModel>();
    museumModels.forEach(actualList::add);
    return museumMapper.toListExistingMuseum(actualList);
  }


  @Override
  public AnswerModel createMuseum(BaseMuseum baseMuseum, String login) {
    UserModel u = userRepository.findByLogin(login);
    if (u != null)
      throw new IllegalArgumentException(ValidationErrorTerms.KEY_NOT_UNIQUE);

    MuseumModel museumModel = museumRepository.save(museumMapper.toMuseumModel(baseMuseum));
    UserModel userModel = new UserModel();
    userModel.setLogin(login);
    userModel.setMuseum(museumModel);
    userModel.setRole(RoleEnum.MUSEUM);
    userRepository.save(userModel);
    return new AnswerModel("Успешное создание музея");
  }

  @Override
  public AnswerModel updateMuseumInfo(UpdatableMuseum updatableMuseum) {

    MuseumModel m = museumRepository.findById(updatableMuseum.getId());
    if (m == null)
      throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);

    if (updatableMuseum.getDescription() != null && !updatableMuseum.getDescription().trim().isEmpty()) {
      m.setDescription(updatableMuseum.getDescription());
    }
    if (updatableMuseum.getImageUrl() != null && !updatableMuseum.getImageUrl().trim().isEmpty()) {
      fileLoaderService.deleteImage(m.getImage());
      m.setImage(updatableMuseum.getImageUrl());
    }
    museumRepository.save(m);
    return new AnswerModel("Успешное обновление данных");
  }

  @Override
  public AnswerModel updateMuseumByAdmin(UpdatableMuseumAdmin updatableMuseum) {

    MuseumModel m = museumRepository.findById(updatableMuseum.getId());
    if (m == null)
      throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
    if (updatableMuseum.getNameMuseum() != null && !updatableMuseum.getNameMuseum().trim().isEmpty()) {
      m.setNameMuseum(updatableMuseum.getNameMuseum());
    }
    if (updatableMuseum.getAddress() != null && !updatableMuseum.getAddress().trim().isEmpty()) {
      m.setAddress(updatableMuseum.getAddress());
    }
    museumRepository.save(m);
    return new AnswerModel("Успешное обновление данных");
  }
}
