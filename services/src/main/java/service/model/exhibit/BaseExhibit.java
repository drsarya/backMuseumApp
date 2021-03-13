package service.model.exhibit;

import museum.domen.AuthorModel;
import museum.domen.ExhibitionModel;

import javax.persistence.*;

public interface BaseExhibit {

  Integer getAuthorId();

  String getName();

  String getImageUrl();

  String getDescription();

  String getDateOfCreate();

  Integer getExhibitionId();
}
