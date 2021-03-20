package service.facade;

import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

public interface ExhibitFacade {

  List<ExistingExhibit> getAllExhibits();

  List<ExistingExhibit> getExhibitsByMuseumId(Integer id);

  ExistingExhibit createExhibit(BaseExhibit exhibit);

  boolean deleteExhibit(int id);

  ExistingExhibit updateExhibit(ExistingExhibit exhibit);
}
