package service.model.museum;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import service.model.author.ImmutableBaseAuthor;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseMuseum.Builder.class)

public interface BaseMuseum {
  String getName();
  String getAddress();
}
