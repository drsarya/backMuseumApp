package service.model.exhibit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import museum.domen.AuthorModel;
import museum.domen.ExhibitionModel;
import org.immutables.value.Value;
import service.model.author.ExistingAuthor;
import service.model.exhibition.ExistingExhibition;

import javax.persistence.*;
@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseExhibit.Builder.class)
public interface BaseExhibit {

  ExistingAuthor getAuthor();

  String getName();

  String getImageUrl();

  String getDescription();

  String getDateOfCreate();

  ExistingExhibition getExhibition();
}
