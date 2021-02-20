package service.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import museum.domen.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import service.model.ExistingUser;
import service.model.NewUser;
import service.model.UserUpdate;

@Mapper
public interface UserStruct {

  @Qualifier
  @Retention(RetentionPolicy.CLASS)
  @interface DefaultStatusMapper {
  }

  @Mapping(target = "id", source = "newId")

   ExistingUser toUserModel(NewUser user, long newId);

   @Mapping(target = "type", source = "user.type")
  @Mapping(target = "login", source = "user.login")
  UserModel toUserModel(NewUser user  );


   @Mapping(target = "login", source = "user.login")
  @Mapping(target = "password", source = "password")
  UserModel toUserModel(UserUpdate user , String password );

  @Mapping(target = "password", source = "password")
  @Mapping(target = "type", source = "user.type")
  @Mapping(target = "login", source = "user.login")
  UserModel toUserModel(NewUser user, String password );

  @Mapping(target = "type", source = "model.type")
  @Mapping(target = "login", source = "model.login")
  @Mapping(target = "password", source = "model.password")
  @Mapping(target = "id", source = "model.id")

  ExistingUser toExistingUser(UserModel model );


}
