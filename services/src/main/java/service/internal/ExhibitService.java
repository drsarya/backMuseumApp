package service.internal;

import org.springframework.web.multipart.MultipartFile;
import service.model.AnswerModel;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

public interface ExhibitService {

  List<ExistingExhibit> getAllExhibits();

  List<ExistingExhibit> getExhibitsByMuseumId(Integer id);

  ExistingExhibit createExhibit(BaseExhibit exhibit);

  AnswerModel deleteExhibit(Integer id);

  List<ExistingExhibit> getExhibitsByExhibitionId(Integer id);

  List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser);

  ExistingExhibit updateExhibit(MultipartFile upload, ExistingExhibit exhibit);

}
