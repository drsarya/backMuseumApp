package service.mapper;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import org.mapstruct.Mapping;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;
import java.util.stream.Collectors;

public interface ExhibitStruct {

  @Mapping(target = "id", source = "exhibitionModel.id")
  @Mapping(target = "imageUrl", source = "exhibitionModel.imageUrl")
  @Mapping(target = "name", source = "exhibitionModel.name")
  @Mapping(target = "description", source = "exhibitionModel.description")
  @Mapping(target = "firstDate", source = "exhibitionModel.firstDate")
  @Mapping(target = "lastDate", source = "exhibitionModel.lastDate")
  @Mapping(target = "museumId", source = "exhibitionModel.museum.id")
  ExistingExhibit toExistingExhibit(ExhibitModel exhibitionModel );







  Integer getExhibitionId();


  @Mapping(target = "name", source = "name")
  @Mapping(target = "imageUrl", source = "imageUrl")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "firstDate", source = "authorId")
  @Mapping(target = "lastDate", source = "dateOfCreate")
  @Mapping(target = "museum", source = "exhibitionId")
  ExhibitModel toExhibitModel(BaseExhibit exhibition);

  default List<ExistingExhibit> toListExistingExhibits(List<ExhibitModel> exhibitionModels) {
    return exhibitionModels
      .stream()
      .map(this::toExistingExhibit)
      .collect(Collectors.toList());
  }
}
