package service.facade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import service.model.OkModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.museum.UpdatableMuseumAdmin;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface MuseumFacade {
  OkModel createMuseum(@Valid BaseMuseum baseMuseum, String login);

  List<ExistingMuseum> getAllMuseums();

  ExistingMuseum getMuseumById(Integer id);

  OkModel updateMuseum(UpdatableMuseum baseMuseum);

  OkModel updateMuseumByAdmin(UpdatableMuseumAdmin baseMuseum);

  OkModel lockMuseum(Integer id);

  OkModel deleteMuseum(Integer id);

  OkModel getOwnerByMuseumId(Integer id);

}
