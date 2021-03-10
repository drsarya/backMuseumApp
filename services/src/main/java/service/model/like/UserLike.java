package service.model.like;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUserLike.Builder.class)
public interface UserLike extends BaseLike{
  Integer getUserId();
}
