package service.model.museum;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdatableMuseumAdmin.Builder.class)
public interface UpdatableMuseumAdmin {
  Long getId();

  String getNameMuseum();

  String getAddress();
}
