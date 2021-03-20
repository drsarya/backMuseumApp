package service.model.exhibit;

import org.immutables.value.Value;
import service.model.exhibition.BaseExhibition;
@Value.Immutable
public interface ExistingExhibit  extends BaseExhibit {
  Integer getId();
}
