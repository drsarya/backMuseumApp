package service.facade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import service.model.OkModel;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;

@Validated
public interface ExhibitionFacade {
  List<ExistingExhibition> getAllExhibitions();

  ExistingExhibition createExhibition(BaseExhibition exhibition);

  ExistingExhibition updateExhibition(MultipartFile file, ExistingExhibition exhibition);

  List<ExistingExhibition> getExhibitionsByMuseumId(Integer id);

  OkModel deleteExhibition(Integer id);

  List<ExistingExhibition> getLikedExhibitionsByUser(Integer idUser);
}
