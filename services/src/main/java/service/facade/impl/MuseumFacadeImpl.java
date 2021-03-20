package service.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.facade.MuseumFacade;
import service.internal.MuseumService;
import service.internal.UserService;
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
  public ExistingMuseum createMuseum(BaseMuseum baseMuseum) {
    return museumService.createMuseum(baseMuseum);
  }

  @Override
  public ExistingMuseum updateMuseum(UpdatableMuseum baseMuseum ) throws IOException {
    return museumService.updateMuseumInfo(baseMuseum );
  }

  @Override
  public ExistingMuseum getMuseumByWorkerId(Integer id) {
    return null;
  }

  @Override
  public ExistingMuseum getMuseumById(Integer id) {
    return null;
  }

  @Override
  public List<ExistingMuseum> getAllMuseums() {
    return null;
  }

  @Override
  public ExistingMuseum getMuseumByLoginAndIdCode(String login, Integer id) {
    return null;
  }

  @Override
  public ExistingMuseum updateMuseumInfo(UpdatableMuseum updatableMuseum) throws IOException {
    return null;
  }
}
