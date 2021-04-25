package service.model.museum;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import validation.ValidationErrorTerms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseMuseum.Builder.class)
public interface BaseMuseum {

  @NotNull(message = ValidationErrorTerms.NAME_MOST_BE_SET)
  @NotEmpty(message = ValidationErrorTerms.NAME_MOST_BE_SET)
  String getName();

  @NotNull(message = ValidationErrorTerms.ADDRESS_MOST_BE_SET)
  @NotEmpty(message = ValidationErrorTerms.ADDRESS_MOST_BE_SET)
  String getAddress();
}

