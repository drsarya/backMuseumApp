package service.model.museum;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
@JsonDeserialize(builder = ImmutableShortInfoMuseum.Builder.class)

public interface ShortInfoMuseum {
  Integer getId();

  @Nullable
  String getName();
}
