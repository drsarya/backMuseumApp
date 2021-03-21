package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.ExhibitionFacade;
import service.internal.ExhibitService;
import service.internal.ExhibitionService;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;

@Service
public class ExhibitionFacadeImpl implements ExhibitionFacade {

  private final ExhibitionService exhibitionService;

  public ExhibitionFacadeImpl(final ExhibitionService exhibitionService) {
    this.exhibitionService = exhibitionService;
  }

  @Override
  public List<ExistingExhibition> getAllExhibitions() {
    return exhibitionService.getAllExhibitions();
  }

  @Override
  public ExistingExhibition createExhibition(BaseExhibition exhibition) {
    return exhibitionService.createExhibition(exhibition);
  }

  @Override
  public ExistingExhibition updateExhibition(ExistingExhibition exhibition) {
    return exhibitionService.updateExhibition(exhibition);
  }

  @Override
  public List<ExistingExhibition> getExhibitionsByMuseumId(int id) {
    return exhibitionService.getExhibitionsByMuseumId(id);
  }

  @Override
  public boolean deleteExhibition(int id) {
    return exhibitionService.deleteExhibition(id);
  }
}
