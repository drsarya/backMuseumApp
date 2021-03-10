package service.internal;

import service.model.exhibition.ExistingExhibition;

import java.util.List;

public interface ExhibitionService {


  List<ExistingExhibition> getAllExhibitions();

  void createExhibition(ExistingExhibition exhibition);

  void updateExhibition(ExistingExhibition exhibition);

  void getExhibitionByMuseumId(int id);

  void deleteExhibition(int id);
}
