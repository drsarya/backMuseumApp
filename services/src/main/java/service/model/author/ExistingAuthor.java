package service.model.author;

import org.immutables.value.Value;

@Value.Immutable
public interface ExistingAuthor extends  BaseAuthor{
  Integer getId();
}
