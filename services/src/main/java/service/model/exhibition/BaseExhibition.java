package service.model.exhibition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import museum.domen.MuseumModel;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import service.model.museum.ExistingMuseum;
import service.model.museum.ShortInfoMuseum;
import service.model.user.ImmutableUserUpdate;
import validation.ValidationErrorTerms;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
