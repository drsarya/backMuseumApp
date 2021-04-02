package service.facade;

import service.model.OkModel;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

public interface ExhibitFacade {

  List<ExistingExhibit> getAllExhibits();

  List<ExistingExhibit> getExhibitsByMuseumId(Integer id);
  List<ExistingExhibit> getExhibitsByExhibitionId(Integer id);
  ExistingExhibit createExhibit(BaseExhibit exhibit);

  OkModel deleteExhibit(int id);

  ExistingExhibit updateExhibit(ExistingExhibit exhibit);
}
