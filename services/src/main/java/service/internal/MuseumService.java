package service.internal;

import org.springframework.web.multipart.MultipartFile;
import service.model.OkModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

import java.io.IOException;
import java.util.List;

public interface MuseumService {
  List<ExistingMuseum> getAllMuseums();

  OkModel createMuseum(BaseMuseum baseMuseum, String login);

  OkModel updateMuseumInfo(UpdatableMuseum updatableMuseum) throws IOException;

  ExistingMuseum getMuseumByWorkerId(Integer id);


}
