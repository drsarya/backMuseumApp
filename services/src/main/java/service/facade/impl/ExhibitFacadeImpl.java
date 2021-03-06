package service.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.facade.ExhibitFacade;
import service.internal.ExhibitService;
import service.model.AnswerModel;
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
  public List<ExistingExhibit> getExhibitsByExhibitionId(Integer id) {
    return exhibitService.getExhibitsByExhibitionId(id);
  }

  @Override
  public ExistingExhibit createExhibit( BaseExhibit exhibit) {
    return exhibitService.createExhibit(exhibit);
  }

  @Override
  public AnswerModel deleteExhibit(int id) {
    return exhibitService.deleteExhibit(id);
  }

  @Override
  public List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser) {
    return exhibitService.getLikedExhibitsByUser(idUser);
  }

  @Override
  public ExistingExhibit updateExhibit(MultipartFile upload, ExistingExhibit exhibit) {
    return exhibitService.updateExhibit(upload, exhibit);
  }
}
