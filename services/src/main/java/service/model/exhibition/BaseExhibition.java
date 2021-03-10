package service.model.exhibition;

import museum.domen.MuseumModel;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public interface BaseExhibition {

    String getName();

    byte[] getImage();

    String getDescription();

    String getFirstDate();

    String getLastDate();

    Integer getMuseumId();
}
