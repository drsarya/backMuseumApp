package service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableNewUser.Builder.class)

public interface NewUser {
    String getUserLogin();
    String getPassword();
    boolean getType();

}
