package service.model.exhibit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import service.model.exhibition.BaseExhibition;
@Value.Immutable
@JsonDeserialize(builder = ImmutableExistingExhibit.Builder.class)

public interface ExistingExhibit  extends BaseExhibit {
  Integer getId();
}
