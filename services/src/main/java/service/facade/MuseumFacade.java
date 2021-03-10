package service.facade;

import org.springframework.web.multipart.MultipartFile;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

import java.io.IOException;

public interface MuseumFacade {
  ExistingMuseum createMuseum(BaseMuseum baseMuseum);
  ExistingMuseum updateMuseum(UpdatableMuseum baseMuseum, MultipartFile upload ) throws IOException;

}
