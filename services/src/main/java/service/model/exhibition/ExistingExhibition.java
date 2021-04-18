package service.model.exhibition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableExistingExhibition.Builder.class)
public interface ExistingExhibition  extends BaseExhibition{
  Integer getId();
}
