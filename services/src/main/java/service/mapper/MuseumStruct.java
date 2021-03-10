package service.mapper;

import museum.domen.MuseumModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;

@Mapper
public interface MuseumStruct {
  @Mapping(target = "login", source =   "login")
  @Mapping(target = "nameMuseum", source =   "name")
  @Mapping(target = "address", source =   "address")
  MuseumModel toMuseumModel(BaseMuseum baseMuseum);

  @Mapping(target = "login", source =   "model.login")
  @Mapping(target = "name", source =   "model.nameMuseum")
  @Mapping(target = "address", source =   "model.address")
  @Mapping(target = "id", source =   "model.id")
  @Mapping(target = "description", source =   "model.description")
  ExistingMuseum toExistingMuseum(MuseumModel model);
}
