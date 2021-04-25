package service.model.exhibition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import service.model.museum.ShortInfoMuseum;
import validation.ValidationErrorTerms;

import javax.validation.constraints.NotEmpty;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseExhibition.Builder.class)
public interface BaseExhibition {
  @NotEmpty(message = ValidationErrorTerms.NAME_MOST_BE_SET)
  String getName();

  @Nullable
  String getImageUrl();

  @NotEmpty(message = ValidationErrorTerms.DESCRIPTION_MOST_BE_SET)
  String getDescription();

  @Nullable
  String getFirstDate();

  @Nullable
  String getLastDate();

  ShortInfoMuseum getMuseum();
}
