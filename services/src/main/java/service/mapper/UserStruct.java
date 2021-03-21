package service.mapper;

import museum.domen.MuseumModel;
import museum.domen.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.museum.ExistingMuseum;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserUpdate;


@Mapper
public interface UserStruct {

  @Mapping(target = "login", source = "user.login")
  @Mapping(target = "password", source = "password")
  UserModel toUserModel(UserUpdate user, String password);

  @Mapping(target = "password", source = "password")
  @Mapping(target = "role", source = "user.role")
  @Mapping(target = "login", source = "user.login")
  @Mapping(target = "museum", source = "museumModel")
  UserModel toUserModel(NewUser user, String password , MuseumModel museumModel);


  @Mapping(target = "role", source = "model.role")
  @Mapping(target = "login", source = "model.login")
  @Mapping(target = "password", source = "model.password")
  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "museum", source = "existingMuseum")
  ExistingUser toExistingUser(UserModel model , ExistingMuseum existingMuseum);


}
