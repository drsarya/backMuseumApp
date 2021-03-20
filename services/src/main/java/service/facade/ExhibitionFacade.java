package service.facade;

import service.model.exhibition.ExistingExhibition;

import java.util.List;

public interface ExhibitionFacade {
  List<ExistingExhibition> getAllExhibitions();

  ExistingExhibition createExhibition(ExistingExhibition exhibition);

  ExistingExhibition updateExhibition(ExistingExhibition exhibition);

  List<ExistingExhibition> getExhibitionsByMuseumId(int id);

  boolean deleteExhibition(int id);
}
