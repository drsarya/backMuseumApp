package service.facade;

import org.springframework.validation.annotation.Validated;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;
@Validated
public interface ExhibitionFacade {
  List<ExistingExhibition> getAllExhibitions();

  ExistingExhibition createExhibition(BaseExhibition exhibition);

  ExistingExhibition updateExhibition(ExistingExhibition exhibition);

  List<ExistingExhibition> getExhibitionsByMuseumId(int id);

  boolean deleteExhibition(int id);
}
