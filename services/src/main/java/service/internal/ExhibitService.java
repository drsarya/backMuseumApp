package service.internal;

import service.model.OkModel;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

public interface ExhibitService {

  List<ExistingExhibit> getAllExhibits();

  List<ExistingExhibit> getExhibitsByMuseumId(Integer id);

  ExistingExhibit createExhibit(BaseExhibit exhibit);

  OkModel deleteExhibit(int id);
  List<ExistingExhibit> getExhibitsByExhibitionId(Integer id);

  ExistingExhibit updateExhibit(ExistingExhibit exhibit);

 }
