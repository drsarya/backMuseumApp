package service.model.user;

import org.immutables.value.Value;

@Value.Immutable
public interface ExistingUser extends NewUser {
  Integer getId();
}
