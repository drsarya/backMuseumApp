package service.model.museum;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdatableMuseum.Builder.class)
public interface UpdatableMuseum {
  Long getId();

  String getNameMuseum();

  String getAddress();
  String getDescription();

}
