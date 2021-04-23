package service.facade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import service.model.OkModel;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

@Validated
public interface ExhibitFacade {

  List<ExistingExhibit> getAllExhibits();

  List<ExistingExhibit> getExhibitsByMuseumId(Integer id);

  List<ExistingExhibit> getExhibitsByExhibitionId(Integer id);

  ExistingExhibit createExhibit(BaseExhibit exhibit);

  OkModel deleteExhibit(int id);

  List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser);

  ExistingExhibit updateExhibit(MultipartFile upload, ExistingExhibit exhibit);
}
