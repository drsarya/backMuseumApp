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
  public ExistingMuseum updateMuseum(UpdatableMuseum baseMuseum, MultipartFile loadFile) throws IOException {
    return museumService.updateMuseumInfo(baseMuseum, loadFile);
  }
}
