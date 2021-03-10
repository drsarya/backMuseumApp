package service.internal;

import org.springframework.web.multipart.MultipartFile;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

import java.io.IOException;
import java.util.List;

public interface MuseumService {
  ExistingMuseum getMuseumByLogin(String login);

  List<ExistingMuseum> getMuseumById(Integer id);

  List<ExistingMuseum> getAllMuseums();

  ExistingMuseum getMuseumByLoginAndIdCode(String login, Integer id);

  ExistingMuseum createMuseum(BaseMuseum baseMuseum);
  ExistingMuseum updateMuseumInfo(UpdatableMuseum updatableMuseum, MultipartFile upload ) throws IOException;
//  boolean updateMuseumImage(UpdatableMuseum updatableMuseum);
//
//  boolean updateMuseumDescription(UpdatableMuseum updatableMuseum);
//
//  boolean updateMuseumInfoByAdmin(UpdatableMuseum updatableMuseum);
}
