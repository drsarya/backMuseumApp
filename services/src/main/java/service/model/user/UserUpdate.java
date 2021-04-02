package service.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUserUpdate.Builder.class)
public interface UserUpdate extends ExistingUser {
  String getNewPassword();
}
