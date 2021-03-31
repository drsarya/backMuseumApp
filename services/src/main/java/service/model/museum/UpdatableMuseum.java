package service.model.museum;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdatableMuseum.Builder.class)
public interface UpdatableMuseum {
  Long getId();
  String getNameMuseum();
  String getAddress();
  @Nullable
  String getDescription();
  @Nullable
  String getImageUrl();
}
