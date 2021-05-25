package service.facade;

import org.springframework.validation.annotation.Validated;
import service.model.AnswerModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.museum.UpdatableMuseumAdmin;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface MuseumFacade {
  AnswerModel createMuseum(@Valid BaseMuseum baseMuseum, String login);

  List<ExistingMuseum> getAllMuseums();

  ExistingMuseum getMuseumById(Integer id);

  AnswerModel updateMuseum(UpdatableMuseum baseMuseum);

  AnswerModel updateMuseumByAdmin(UpdatableMuseumAdmin baseMuseum);

  AnswerModel lockMuseum(Integer id);

  AnswerModel deleteMuseum(Integer id);

  AnswerModel getOwnerByMuseumId(Integer id);
  List<ExistingMuseum> getAllActiveMuseums();

}
