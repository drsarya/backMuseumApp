package service.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.facade.MuseumFacade;
import service.internal.MuseumService;
import service.internal.UserService;
import service.model.OkModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

import java.io.IOException;
import java.util.List;

@Service
public class MuseumFacadeImpl implements MuseumFacade {

  private final MuseumService museumService;

  public MuseumFacadeImpl(final MuseumService museumService) {
    this.museumService = museumService;
  }

  @Override
  public OkModel createMuseum(BaseMuseum baseMuseum, String login) {
    return museumService.createMuseum(baseMuseum, login);
  }

  @Override
  public OkModel updateMuseum(UpdatableMuseum baseMuseum) throws IOException {
    return museumService.updateMuseumInfo(baseMuseum);
  }

  @Override
  public ExistingMuseum getMuseumByWorkerId(Integer id) {
    return museumService.getMuseumByWorkerId(id);
  }

  @Override
  public List<ExistingMuseum> getAllMuseums() {
    return museumService.getAllMuseums();
  }


}
