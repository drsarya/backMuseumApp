package service.facade;

import org.springframework.web.multipart.MultipartFile;
import service.model.OkModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

import java.io.IOException;
import java.util.List;

public interface MuseumFacade {
  OkModel createMuseum(BaseMuseum baseMuseum, String login);

  List<ExistingMuseum> getAllMuseums();

  OkModel updateMuseum(UpdatableMuseum baseMuseum) throws IOException;

  ExistingMuseum getMuseumByWorkerId(Integer id);

 }
