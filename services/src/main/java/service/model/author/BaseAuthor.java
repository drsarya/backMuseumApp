package service.model.author;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import service.model.user.ImmutableNewUser;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseAuthor.Builder.class)
public interface BaseAuthor {
    String getFullName();
}
