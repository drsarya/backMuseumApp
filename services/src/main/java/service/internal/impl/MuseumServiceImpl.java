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
  public ExistingMuseum getMuseumByWorkerId(Integer id) {
    MuseumModel museumModel = museumMapper.findMuseumByUserId(id);
    if (museumModel != null) {
      return museumStruct.toExistingMuseum(museumModel);
    }
    return null;
  }

  @Override
  public OkModel blockMuseum(Integer id) {
    MuseumModel museumModel = museumMapper.findById((long) id);

    if (museumModel != null) {
      if (museumModel.getState() == MuseumStateEnum.BLOCKED)
        throw new IllegalArgumentException("Музей уже заблокирован");
      if (museumModel.getState() == MuseumStateEnum.NOT_ACTIVE)
        throw new IllegalArgumentException("Музей е может быть заблокирован");
      museumModel.setState(MuseumStateEnum.BLOCKED);
      return new OkModel("Музей заблокирован");
    }
    throw new IllegalArgumentException("Музей не найден");
  }

  @Override
  public OkModel deleteMuseum(Integer id) {
    MuseumModel museumModel = museumMapper.findById((long) id);
    if (museumModel != null) {
      if (museumModel.getState() == MuseumStateEnum.BLOCKED || museumModel.getState() == MuseumStateEnum.ACTIVE)
        throw new IllegalArgumentException("Музей не может быть удален");
      museumMapper.delete((long) id);
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
  public OkModel updateMuseumInfo(UpdatableMuseum updatableMuseum) throws IOException {

    Long id = updatableMuseum.getId();
    MuseumModel m = museumMapper.findById(updatableMuseum.getId());
    if (m == null)
      throw new IllegalArgumentException("Музей не найден");
    if (!updatableMuseum.getNameMuseum().isEmpty()) {
      m.setNameMuseum(updatableMuseum.getNameMuseum());
    }
    if (!updatableMuseum.getAddress().isEmpty()) {
      m.setAddress(updatableMuseum.getAddress());
    }
    if (updatableMuseum.getDescription() != null) {
      m.setDescription(updatableMuseum.getDescription());
    }
    if (updatableMuseum.getImageUrl() != null) {
      m.setImage(updatableMuseum.getImageUrl());
    }
    museumMapper.save(m);
    return new OkModel("Успешное обновление данных");
  }
}
