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
  @Mapping(target = "exhibitionId", source = "idExhbtn")
  @Mapping(target = "id", source = "exhibitModel.id")
   ExistingExhibit toExistingExhibit(ExhibitModel exhibitModel, ExistingAuthor author, Integer idExhbtn);


  @Mapping(target = "name", source = "exhibit.name")
  @Mapping(target = "imageUrl", source = "exhibit.imageUrl")
  @Mapping(target = "description", source = "exhibit.description")
  @Mapping(target = "dateOfCreate", source = "exhibit.dateOfCreate")
  @Mapping(target = "author", source = "exhibit.author")
  @Mapping(target = "exhibition", source = "exhibitionModel")
  ExhibitModel toExhibitModel(BaseExhibit exhibit, ExhibitionModel exhibitionModel);


}
