package service.model.exhibition;

import org.immutables.value.Value;

@Value.Immutable
public interface ExistingExhibition  extends BaseExhibition{
  Integer getId();
}
