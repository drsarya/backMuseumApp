package service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import src.model.RoleEnum;

@Value.Immutable
@JsonDeserialize(builder = ImmutableNewUser.Builder.class)

public interface NewUser {
    String getLogin();
    @Nullable
    String getPassword();
    RoleEnum getRole();

}
