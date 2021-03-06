package service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUserUpdate.Builder.class)

public interface UserUpdate extends  NewUser{
  String getNewPassword();
}
