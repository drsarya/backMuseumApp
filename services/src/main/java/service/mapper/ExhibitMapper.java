package service.mapper;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.author.ExistingAuthor;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

@Mapper
public interface ExhibitMapper {


  @Mapping(target = "name", source = "exhibitModel.name")
  @Mapping(target = "imageUrl", source = "exhibitModel.imageUrl")
  @Mapping(target = "description", source = "exhibitModel.description")
  @Mapping(target = "dateOfCreate", source = "exhibitModel.dateOfCreate")
  @Mapping(target = "author", source = "exhibitModel.author")
  @Mapping(target = "exhibitionId", source = "exhibitModel.exhibition.id")
  @Mapping(target = "id", source = "exhibitModel.id")
  ExistingExhibit toExistingExhibit(ExhibitModel exhibitModel );


  @Mapping(target = "name", source = "name")
  @Mapping(target = "imageUrl", source = "imageUrl")
  @Mapping(target = "description", source = "exhibit.description")
  @Mapping(target = "dateOfCreate", source = "dateOfCreate")
  @Mapping(target = "author", source = "author")
  @Mapping(target = "exhibition.id", source = "exhibitionId")
  ExhibitModel toExhibitModel(BaseExhibit exhibit );



}
