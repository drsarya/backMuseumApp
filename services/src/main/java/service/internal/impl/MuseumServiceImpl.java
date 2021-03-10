package service.internal.impl;

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
  public ExistingMuseum getMuseumByLogin(String login) {
    return null;
  }

  @Override
  public List<ExistingMuseum> getMuseumById(Integer id) {
    return null;
  }

  @Override
  public List<ExistingMuseum> getAllMuseums() {
    return null;
  }

  @Override
  public ExistingMuseum getMuseumByLoginAndIdCode(String login, Integer id) {
    return null;
  }

  @Override
  public ExistingMuseum createMuseum(BaseMuseum baseMuseum) {
    return museumStruct.toExistingMuseum(museumMapper.save(museumStruct.toMuseumModel(baseMuseum)));
  }

  /*
  * String getNameMuseum();

    String getAddress();
    String getDescription();
    byte[] getImage();
    * */
  private final Path root = Paths.get("C:\\Users\\PC\\Desktop\\ee");
  @Override
  public ExistingMuseum updateMuseumInfo(UpdatableMuseum updatableMuseum, MultipartFile upload ) throws IOException {
    museumMapper.findById( 1L );
    Long id = updatableMuseum.getId();
    MuseumModel m = museumMapper.findById(updatableMuseum.getId());
    m.setNameMuseum(updatableMuseum.getNameMuseum());
    m.setAddress(updatableMuseum.getAddress());
    m.setDescription(updatableMuseum.getDescription());
     Files.copy(upload.getInputStream(), this.root.resolve(upload.getOriginalFilename()));

    URI u = URI.create("http://java.sun.com/");
//    try (InputStream in = u.toURL().openStream()) {
//      Files.copy(upload.getInputStream(), this.root.resolve(upload.getOriginalFilename())) ;
//
    m.setImage( upload.getOriginalFilename());


    return museumStruct.toExistingMuseum(museumMapper.save(m));
  }
}
