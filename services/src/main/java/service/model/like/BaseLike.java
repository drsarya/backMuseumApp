package service.model.like;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import src.model.TypeOfArtEnum;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseLike.Builder.class)
public interface BaseLike {

  Integer getArtId();

  TypeOfArtEnum getType();
}
