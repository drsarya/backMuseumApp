package service.internal.impl;

import museum.domen.AuthorModel;
import museum.domen.MuseumModel;
import museum.domen.UserModel;
import museum.mapper.MuseumMapper;
import museum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.internal.MuseumService;
import service.mapper.MuseumStruct;
import service.model.OkModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.museum.UpdatableMuseumAdmin;
import src.model.MuseumStateEnum;
import src.model.RoleEnum;

import javax.management.relation.Role;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MuseumServiceImpl implements MuseumService {
  @Autowired
  public MuseumServiceImpl(MuseumMapper museumMapper, MuseumStruct museumStruct, UserMapper userMapper) {
    this.museumMapper = museumMapper;
    this.museumStruct = museumStruct;
    this.userMapper = userMapper;
  }

  private final UserMapper userMapper;
  private final MuseumMapper museumMapper;
  private final MuseumStruct museumStruct;

  @Override
  public OkModel getOwnerByMuseumId(Integer id) {
    MuseumModel museumModel = museumMapper.findById((long)id);
    if (museumModel != null) {
      return new OkModel(museumModel.getWorker().getLogin());
    }
    throw new IllegalArgumentException("К музею не привязан работник");
  }

  @Override
  public ExistingMuseum getMuseumById(Integer id) {

    MuseumModel museumModel = museumMapper.findById((long)id);
    if (museumModel != null) {
      return museumStruct.toExistingMuseum(museumModel);
    }
    throw new IllegalArgumentException("Музей не найден");
  }

  @Override
  public OkModel lockMuseum(Integer id) {
    MuseumModel museumModel = museumMapper.findById((long) id);
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
          throw new IllegalArgumentException("Музей еще не активен");
      }
      return new OkModel(result);
    }
    throw new IllegalArgumentException("Музей не найден");
  }



  @Override
  public OkModel deleteMuseum(Integer id) {
    MuseumModel museumModel = museumMapper.findById((long) id);
    if (museumModel != null) {
      if (museumModel.getState() == MuseumStateEnum.BLOCKED || museumModel.getState() == MuseumStateEnum.ACTIVE)
        throw new IllegalArgumentException("Музей не может быть удален");
      museumMapper.delete(museumModel);
      return new OkModel("Музей удалён");
    }
    throw new IllegalArgumentException("Музей не найден");
  }

  @Override
  public List<ExistingMuseum> getAllMuseums() {
    Iterable<MuseumModel> museumModels = museumMapper.findAll();

    List<MuseumModel> actualList = new ArrayList<MuseumModel>();
    museumModels.forEach(actualList::add);

    if (actualList.size() == 0) throw new IllegalArgumentException("Список музеев пуст");
    return museumStruct.toListExistingMuseum(actualList);
  }


  @Override
  public OkModel createMuseum(BaseMuseum baseMuseum, String login) {
    UserModel u = userMapper.findByLogin(login);
    if (u != null)
      throw new IllegalArgumentException("Ошибка создания, логин уже существует");

    MuseumModel museumModel = museumMapper.save(museumStruct.toMuseumModel(baseMuseum));
    UserModel userModel = new UserModel();
    userModel.setLogin(login);
    userModel.setMuseum(museumModel);
    userModel.setRole(RoleEnum.MUSEUM);
    userMapper.save(userModel);
    return new OkModel("Успешное создание музея");
  }


  // private final Path root = Paths.get("C:\\Users\\PC\\Desktop\\ee");
  @Override
  public OkModel updateMuseumInfo(UpdatableMuseum updatableMuseum) {

    Long id = updatableMuseum.getId();
    MuseumModel m = museumMapper.findById(updatableMuseum.getId());
    if (m == null)
      throw new IllegalArgumentException("Музей не найден");

    if (!updatableMuseum.getDescription().trim().isEmpty()) {
      m.setDescription(updatableMuseum.getDescription());
    }
    if (!updatableMuseum.getImageUrl().trim().isEmpty()) {
      m.setImage(updatableMuseum.getImageUrl());
    }
    museumMapper.save(m);
    return new OkModel("Успешное обновление данных");
  }

  @Override
  public OkModel updateMuseumByAdmin(UpdatableMuseumAdmin updatableMuseum) {
    Long id = updatableMuseum.getId();
    MuseumModel m = museumMapper.findById(updatableMuseum.getId());
    if (m == null)
      throw new IllegalArgumentException("Музей не найден");
    if (!updatableMuseum.getNameMuseum().trim().isEmpty()) {
      m.setNameMuseum(updatableMuseum.getNameMuseum());
    }
    if (!updatableMuseum.getAddress().trim().isEmpty()) {
      m.setAddress(updatableMuseum.getAddress());
    }
    museumMapper.save(m);
    return new OkModel("Успешное обновление данных");
  }
}
