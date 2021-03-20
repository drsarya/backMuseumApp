package service.facade;

import org.springframework.web.multipart.MultipartFile;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

import java.io.IOException;
import java.util.List;

public interface MuseumFacade {
  ExistingMuseum createMuseum(BaseMuseum baseMuseum);
  ExistingMuseum updateMuseum(UpdatableMuseum baseMuseum ) throws IOException;
  ExistingMuseum getMuseumByWorkerId(Integer id);

  ExistingMuseum getMuseumById(Integer id);

  List<ExistingMuseum> getAllMuseums();

  ExistingMuseum getMuseumByLoginAndIdCode(String login, Integer id);



  ExistingMuseum updateMuseumInfo(UpdatableMuseum updatableMuseum) throws IOException;
}
