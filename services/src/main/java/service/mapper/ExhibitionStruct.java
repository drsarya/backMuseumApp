package service.mapper;

import museum.domen.AuthorModel;
import museum.domen.ExhibitionModel;
import museum.domen.MuseumModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;
import service.model.museum.ExistingMuseum;
import service.model.museum.ShortInfoMuseum;

import java.util.List;
import java.util.stream.Collectors;
@Mapper
public interface ExhibitionStruct {

  @Mapping(target = "id", source = "exhibitionModel.id")
  @Mapping(target = "imageUrl", source = "exhibitionModel.imageUrl")
  @Mapping(target = "name", source = "exhibitionModel.name")
  @Mapping(target = "description", source = "exhibitionModel.description")
  @Mapping(target = "firstDate", source = "exhibitionModel.firstDate")
  @Mapping(target = "lastDate", source = "exhibitionModel.lastDate")
  @Mapping(target = "museum", source = "museum" )
  ExistingExhibition toExistingExhibition(ExhibitionModel exhibitionModel, ShortInfoMuseum museum);


  @Mapping(target = "name", source = "exhibition.name")
  @Mapping(target = "imageUrl", source = "exhibition.imageUrl")
  @Mapping(target = "description", source = "exhibition.description")
  @Mapping(target = "firstDate", source = "exhibition.firstDate")
  @Mapping(target = "lastDate", source = "exhibition.lastDate")
  @Mapping(target = "museum", source = "museumModel")
  ExhibitionModel toExhibitionModel(BaseExhibition exhibition, MuseumModel museumModel);


}
