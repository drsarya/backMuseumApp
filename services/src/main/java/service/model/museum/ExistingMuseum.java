package service.model.museum;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import javax.persistence.Column;

@Value.Immutable
@JsonDeserialize(builder = ImmutableExistingMuseum.Builder.class)

public interface ExistingMuseum extends BaseMuseum {
  Integer getId();

  @Nullable
  String getDescription();

  @Nullable
  String getImageUrl();


}
