package service.facade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import service.model.OkModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@Validated
public interface MuseumFacade {
  OkModel createMuseum( @Valid  BaseMuseum baseMuseum, String login);

  List<ExistingMuseum> getAllMuseums();

  OkModel updateMuseum(UpdatableMuseum baseMuseum) throws IOException;
  OkModel blockMuseum(Integer id)  ;
  OkModel deleteMuseum(Integer id)  ;
  ExistingMuseum getMuseumByWorkerId(Integer id);

 }
