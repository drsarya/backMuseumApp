package service.model.museum;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import javax.persistence.Column;

@Value.Immutable
public interface ExistingMuseum extends BaseMuseum {
  Integer getId();

  @Nullable
  String getDescription();

  @Nullable
  String getImageUrl();


}
