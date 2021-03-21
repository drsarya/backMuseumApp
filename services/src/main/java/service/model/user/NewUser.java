package service.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import service.model.museum.ExistingMuseum;
import src.model.RoleEnum;

@Value.Immutable
@JsonDeserialize(builder = ImmutableNewUser.Builder.class)
public interface NewUser {
  String getLogin();

  @Nullable
  String getPassword();

  RoleEnum getRole();
  @Nullable
  ExistingMuseum getMuseum();
}
