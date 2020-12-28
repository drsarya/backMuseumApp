package service.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Instant;

import museum.app.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import service.model.ExistingUser;
import service.model.NewUser;

@Mapper
public interface UserStruct {

  @Qualifier
  @Retention(RetentionPolicy.CLASS)
  @interface DefaultStatusMapper {
  }

  @Mapping(target = "id", source = "newId")

   ExistingUser fromNewUser(NewUser user, long newId);

   @Mapping(target = "type", source = "user.type")
  @Mapping(target = "login", source = "user.login")
  UserModel fromNewUser(NewUser user  );

  @Mapping(target = "password", source = "password")
  @Mapping(target = "type", source = "user.type")
  @Mapping(target = "login", source = "user.login")
  UserModel fromNewUser(NewUser user, String password );

  @Mapping(target = "type", source = "model.type")
  @Mapping(target = "login", source = "model.login")
  @Mapping(target = "password", source = "model.password")
  @Mapping(target = "id", source = "model.id")

  ExistingUser toExistingUser(UserModel model );


}
