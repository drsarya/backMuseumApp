package service.model.exhibit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import museum.domen.AuthorModel;
import museum.domen.ExhibitionModel;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;
import service.model.exhibition.ExistingExhibition;
import validation.ValidationErrorTerms;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseExhibit.Builder.class)
public interface BaseExhibit {

  BaseAuthor getAuthor();

  @NotEmpty(message = ValidationErrorTerms.NAME_MOST_BE_SET)
  String getName();

  @Nullable
  String getImageUrl();

  @NotEmpty(message = ValidationErrorTerms.DESCRIPTION_MOST_BE_SET)
  String getDescription();

  @NotEmpty(message = ValidationErrorTerms.DATE_MOST_BE_SET)
  String getDateOfCreate();

  @Nullable
  Integer getExhibitionId();
}
