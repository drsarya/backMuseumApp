package service.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.facade.ExhibitionFacade;
import service.internal.ExhibitionService;
import service.model.AnswerModel;
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
  public ExistingExhibition updateExhibition(MultipartFile file, ExistingExhibition exhibition) {
    return exhibitionService.updateExhibition(file, exhibition);
  }

  @Override
  public List<ExistingExhibition> getExhibitionsByMuseumId(Integer id) {
    return exhibitionService.getExhibitionsByMuseumId(id);
  }

  @Override
  public AnswerModel deleteExhibition(Integer id) {
    return exhibitionService.deleteExhibition(id);
  }

  @Override
  public List<ExistingExhibition> getLikedExhibitionsByUser(Integer idUser) {
    return exhibitionService.getLikedExhibitionsByUser(idUser);
  }
}
