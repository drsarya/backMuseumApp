package service.mapper;

import museum.domen.AuthorModel;
import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.mapper.AuthorMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.mapper.customMap.ExhibitDetailStruct;
import service.model.author.ExistingAuthor;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ExhibitStruct {


  @Mapping(target = "name", source = "exhibitModel.name")
  @Mapping(target = "imageUrl", source = "exhibitModel.imageUrl")
  @Mapping(target = "description", source = "exhibitModel.description")
  @Mapping(target = "dateOfCreate", source = "exhibitModel.dateOfCreate")
  @Mapping(target = "author", source = "author")
  @Mapping(target = "exhibition", source = "exhibition")
  @Mapping(target = "id", source = "exhibitModel.id")
   ExistingExhibit toExistingExhibit(ExhibitModel exhibitModel, ExistingAuthor author, ExistingExhibition exhibition);


  @Mapping(target = "name", source = "name")
  @Mapping(target = "imageUrl", source = "imageUrl")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "dateOfCreate", source = "dateOfCreate")
  @Mapping(target = "author", source = "author")
  @Mapping(target = "exhibition", source = "exhibition")
  ExhibitModel toExhibitModel(BaseExhibit exhibit);


}
