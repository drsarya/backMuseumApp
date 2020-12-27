package service.mapper;

import dataBase.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.ExistingUser;
import service.model.NewUser;

@Mapper
public interface UserStruct {



  @Mapping(target = "type", source = "user.type")
  @Mapping(target = "password", source = "user.password")
  @Mapping(target = "login", source = "user.login")

   UserModel fromNewUser(NewUser user );

  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "login", source = "model.login")
   @Mapping(target = "password", source = "model.password")
  @Mapping(target = "type", source = "model.type")

  ExistingUser toExistingUser(UserModel model );

}
