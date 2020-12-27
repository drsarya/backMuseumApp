package service.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import service.model.ExistingUser;
import service.model.NewUser;

@Mapper
public interface UserMapper {

  @Qualifier
  @Retention(RetentionPolicy.CLASS)
  @interface DefaultStatusMapper {
  }

  @Mapping(target = "id", source = "newId")
   ExistingUser fromNewUser(NewUser user, long newId);



}
