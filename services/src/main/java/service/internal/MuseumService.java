package service.internal;

import org.springframework.web.multipart.MultipartFile;
import service.model.OkModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.museum.UpdatableMuseumAdmin;

import java.io.IOException;
import java.util.List;

public interface MuseumService {
  List<ExistingMuseum> getAllMuseums();

  OkModel createMuseum(BaseMuseum baseMuseum, String login);

  OkModel updateMuseumByAdmin(UpdatableMuseumAdmin updatableMuseum);

  OkModel updateMuseumInfo(UpdatableMuseum baseMuseum);

  OkModel getOwnerByMuseumId(Integer id);
  ExistingMuseum getMuseumById(Integer id);

  OkModel lockMuseum(Integer id);


  OkModel deleteMuseum(Integer id);

}
