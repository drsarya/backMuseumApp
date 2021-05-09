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
public interface UserMapper {

  @Mapping(target = "password", source = "password")
  @Mapping(target = "role", source = "user.role")
  @Mapping(target = "login", source = "user.login")
  @Mapping(target = "museum.id", source = "user.museumId")
  UserModel toUserMuseumModel(NewUser user, String password );
  @Mapping(target = "password", source = "password")
  @Mapping(target = "role", source = "user.role")
  @Mapping(target = "login", source = "user.login")
  UserModel toUserModel(NewUser user, String password );

  @Mapping(target = "role", source = "model.role")
  @Mapping(target = "login", source = "model.login")
  @Mapping(target = "password", source = "model.password")
  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "museumId", source = "model.museum.id")
  ExistingUser toExistingUser(UserModel model );

}
