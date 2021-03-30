package service.facade;

import org.springframework.web.multipart.MultipartFile;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

import java.io.IOException;
import java.util.List;

public interface MuseumFacade {
  ExistingMuseum createMuseum(BaseMuseum baseMuseum);

  List<ExistingMuseum> getAllMuseums();

  ExistingMuseum updateMuseum(UpdatableMuseum baseMuseum) throws IOException;

  ExistingMuseum getMuseumByWorkerId(Integer id);

 }
