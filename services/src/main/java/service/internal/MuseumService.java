package service.internal;

import service.model.AnswerModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.museum.UpdatableMuseumAdmin;

import java.util.List;

public interface MuseumService {
  List<ExistingMuseum> getAllMuseums();

  List<ExistingMuseum> getAllActiveMuseums();

  AnswerModel createMuseum(BaseMuseum baseMuseum, String login);

  AnswerModel updateMuseumByAdmin(UpdatableMuseumAdmin updatableMuseum);

  AnswerModel updateMuseumInfo(UpdatableMuseum baseMuseum);

  AnswerModel getOwnerByMuseumId(Integer id);

  ExistingMuseum getMuseumById(Integer id);

  AnswerModel lockMuseum(Integer id);

  AnswerModel deleteMuseum(Integer id);

  ExistingMuseum activateMuseum(Integer id);

}
