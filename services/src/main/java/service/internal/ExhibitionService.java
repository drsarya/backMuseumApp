package service.internal;

import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;

public interface ExhibitionService {


  List<ExistingExhibition> getAllExhibitions();

  ExistingExhibition createExhibition(BaseExhibition exhibition);

  ExistingExhibition updateExhibition(ExistingExhibition exhibition);

  List<ExistingExhibition> getExhibitionsByMuseumId(int id);

  boolean deleteExhibition(int id);
}
