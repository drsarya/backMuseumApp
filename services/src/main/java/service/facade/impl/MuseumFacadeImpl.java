package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.MuseumFacade;
import service.internal.MuseumService;
import service.model.OkModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.museum.UpdatableMuseumAdmin;

import javax.validation.Valid;
import java.util.List;

@Service
public class MuseumFacadeImpl implements MuseumFacade {

  private final MuseumService museumService;

  public MuseumFacadeImpl(final MuseumService museumService) {
    this.museumService = museumService;
  }

  @Override
  public OkModel createMuseum(@Valid  BaseMuseum baseMuseum, String login) {
    return museumService.createMuseum(baseMuseum, login);
  }

  @Override
  public OkModel updateMuseum(UpdatableMuseum baseMuseum)  {
    return museumService.updateMuseumInfo(baseMuseum);
  }

  @Override
  public OkModel updateMuseumByAdmin(UpdatableMuseumAdmin baseMuseum)  {
    return museumService.updateMuseumByAdmin(baseMuseum);
  }

  @Override
  public OkModel lockMuseum(Integer id) {
    return museumService.lockMuseum(id);
  }

  @Override
  public OkModel deleteMuseum(Integer id) {
    return museumService.deleteMuseum(id);
  }

  @Override
  public OkModel getOwnerByMuseumId(Integer id) {
    return museumService.getOwnerByMuseumId(id);
  }

  @Override
  public List<ExistingMuseum> getAllMuseums() {
    return museumService.getAllMuseums();
  }

  @Override
  public ExistingMuseum getMuseumById(Integer id) {
    return museumService.getMuseumById(id);
  }


}
