package service.internal.impl;

import museum.domen.AuthorModel;
import museum.domen.MuseumModel;
import museum.mapper.MuseumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.internal.MuseumService;
import service.mapper.MuseumStruct;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

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
  public MuseumServiceImpl(MuseumMapper museumMapper, MuseumStruct museumStruct) {
    this.museumMapper = museumMapper;
    this.museumStruct = museumStruct;
  }

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

//

  @Override
  public List<ExistingMuseum> getAllMuseums() {
    Iterable<MuseumModel> museumModels = museumMapper.findAll();
    List<MuseumModel> actualList = new ArrayList<MuseumModel>();
    while (museumModels.iterator().hasNext()) {
      actualList.add(museumModels.iterator().next());
    }
    return museumStruct.toListExistingMuseum(actualList);
  }


  @Override
  public ExistingMuseum createMuseum(BaseMuseum baseMuseum) {
    return museumStruct.toExistingMuseum(museumMapper.save(museumStruct.toMuseumModel(baseMuseum)));
  }


  // private final Path root = Paths.get("C:\\Users\\PC\\Desktop\\ee");
  @Override
  public ExistingMuseum updateMuseumInfo(UpdatableMuseum updatableMuseum) throws IOException {
    museumMapper.findById(1L);
    Long id = updatableMuseum.getId();
    MuseumModel m = museumMapper.findById(updatableMuseum.getId());
    if (!updatableMuseum.getNameMuseum().isEmpty()) {
      m.setNameMuseum(updatableMuseum.getNameMuseum());
    }
    if (!updatableMuseum.getAddress().isEmpty()) {
      m.setAddress(updatableMuseum.getAddress());
    }
    if (!updatableMuseum.getDescription().isEmpty()) {
      m.setDescription(updatableMuseum.getDescription());
    }
    if (!updatableMuseum.getImageUrl().isEmpty()) {
      m.setImage(updatableMuseum.getImageUrl());
    }
    return museumStruct.toExistingMuseum(museumMapper.save(m));
  }
}
