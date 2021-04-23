package service.model.like;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableExistingLike.Builder.class)
public interface ExistingLike extends BaseLike {
  Integer getId();
}
