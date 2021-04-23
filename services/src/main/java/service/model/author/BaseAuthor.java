package service.model.author;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import service.model.user.ImmutableNewUser;
import validation.ValidationErrorTerms;

import javax.validation.constraints.NotEmpty;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseAuthor.Builder.class)
public interface BaseAuthor {

  @NotEmpty(message = ValidationErrorTerms.NAME_MOST_BE_SET)
  String getFullName();
}
