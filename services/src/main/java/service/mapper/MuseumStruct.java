package service.mapper;

import museum.domen.AuthorModel;
import museum.domen.MuseumModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.author.ExistingAuthor;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface MuseumStruct {
  @Mapping(target = "nameMuseum", source = "name")
  @Mapping(target = "address", source = "address")
  MuseumModel toMuseumModel(BaseMuseum baseMuseum);

  @Mapping(target = "name", source = "model.nameMuseum")
  @Mapping(target = "address", source = "model.address")
  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "imageUrl", source = "model.image")
  @Mapping(target = "description", source = "model.description")
  ExistingMuseum toExistingMuseum(MuseumModel model);

  default List<ExistingMuseum> toListExistingMuseum(List<MuseumModel> museumModels) {
    return museumModels
      .stream()
      .map(this::toExistingMuseum)
      .collect(Collectors.toList());
  }
}
