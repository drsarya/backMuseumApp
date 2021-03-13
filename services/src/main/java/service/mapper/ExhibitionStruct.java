package service.mapper;

import museum.domen.AuthorModel;
import museum.domen.ExhibitionModel;
import org.mapstruct.Mapping;
import org.springframework.lang.Nullable;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;
import java.util.stream.Collectors;

public interface ExhibitionStruct {

  @Mapping(target = "id", source = "exhibitionModel.id")
  @Mapping(target = "imageUrl", source = "exhibitionModel.imageUrl")
  @Mapping(target = "name", source = "exhibitionModel.name")
  @Mapping(target = "description", source = "exhibitionModel.description")
  @Mapping(target = "firstDate", source = "exhibitionModel.firstDate")
  @Mapping(target = "lastDate", source = "exhibitionModel.lastDate")
  @Mapping(target = "museumId", source = "exhibitionModel.museum.id")
  ExistingExhibition toExistingExhibition(ExhibitionModel exhibitionModel);


  @Mapping(target = "name", source = "name")
  @Mapping(target = "imageUrl", source = "imageUrl")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "firstDate", source = "firstDate")
  @Mapping(target = "lastDate", source = "lastDate")
  @Mapping(target = "museum", source = "museumId")
  ExhibitionModel toExhibitionModel(BaseExhibition exhibition);

  default List<ExistingExhibition> toListExistingExhibitions(List<ExhibitionModel> exhibitionModels) {
    return exhibitionModels
      .stream()
      .map(this::toExistingExhibition)
      .collect(Collectors.toList());
  }
}
