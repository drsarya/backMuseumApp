package service.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUserMuseum.Builder.class)
public interface UserMuseum extends UserUpdate {
  Integer getIdCode();
}
