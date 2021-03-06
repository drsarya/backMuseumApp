package service.mapper;

import museum.domen.MuseumModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.ShortInfoMuseum;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface MuseumMapper {
  @Mapping(target = "nameMuseum", source = "name")
  @Mapping(target = "address", source = "address")
  MuseumModel toMuseumModel(BaseMuseum baseMuseum);

  @Mapping(target = "name", source = "nameMuseum")
  @Mapping(target = "id", source = "id")
  ShortInfoMuseum toShortInfoMuseum(MuseumModel museumModel);

  @Mapping(target = "nameMuseum", source = "name")
  @Mapping(target = "id", source = "id")
  MuseumModel   toShortInfoMuseum(ShortInfoMuseum museumModel);

  @Mapping(target = "name", source = "model.nameMuseum")
  @Mapping(target = "address", source = "model.address")
  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "imageUrl", source = "model.image")
  @Mapping(target = "description", source = "model.description")
  @Mapping(target = "state", source = "model.state")
  ExistingMuseum toExistingMuseum(MuseumModel model);

  default List<ExistingMuseum> toListExistingMuseum(List<MuseumModel> museumModels) {
    return museumModels
      .stream()
      .map(this::toExistingMuseum)
      .collect(Collectors.toList());
  }
}
