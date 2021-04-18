package service.internal;

import org.springframework.web.multipart.MultipartFile;
import service.model.OkModel;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;

public interface ExhibitionService {


  List<ExistingExhibition> getAllExhibitions();

  ExistingExhibition createExhibition(BaseExhibition exhibition);

  ExistingExhibition updateExhibition(MultipartFile file, ExistingExhibition exhibition);

  List<ExistingExhibition> getExhibitionsByMuseumId(Integer id);
  List<ExistingExhibition>  getLikedExhibitionsByUser(Integer idUser);

  OkModel deleteExhibition(Integer id);
}
