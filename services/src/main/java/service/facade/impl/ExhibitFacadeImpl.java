package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.ExhibitFacade;
import service.internal.AuthorService;
import service.internal.ExhibitService;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.List;
@Service
public class ExhibitFacadeImpl implements ExhibitFacade {
  private final ExhibitService exhibitService;

  public ExhibitFacadeImpl(final ExhibitService exhibitService) {
    this.exhibitService = exhibitService;
  }

  @Override
  public List<ExistingExhibit> getAllExhibits() {
    return exhibitService.getAllExhibits();
  }

  @Override
  public List<ExistingExhibit> getExhibitsByMuseumId(Integer id) {
    return exhibitService.getExhibitsByMuseumId(id);
  }

  @Override
  public ExistingExhibit createExhibit(BaseExhibit exhibit) {
    return exhibitService.createExhibit(exhibit);
  }

  @Override
  public boolean deleteExhibit(int id) {
    return exhibitService.deleteExhibit(id);
  }

  @Override
  public ExistingExhibit updateExhibit(ExistingExhibit exhibit) {
    return exhibitService.updateExhibit(exhibit);
  }
}
