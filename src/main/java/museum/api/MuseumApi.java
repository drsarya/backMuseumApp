package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.facade.MuseumFacade;
import service.model.AnswerModel;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.museum.UpdatableMuseumAdmin;

import java.util.List;

@RestController
@RequestMapping(
  value = "/museum",
  produces = "application/json"
)
public class MuseumApi {

  private final MuseumFacade museumFacade;

  @Autowired
  public MuseumApi(final MuseumFacade museumFacade) {
    this.museumFacade = museumFacade;
  }

  @PostMapping(value = "/{login}", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  AnswerModel createMuseum(@RequestBody final BaseMuseum baseMuseum, @PathVariable String login) {
    return museumFacade.createMuseum(baseMuseum, login);
  }

  @PutMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  AnswerModel updateMuseum(@RequestBody UpdatableMuseum updatableMuseum) {
    return museumFacade.updateMuseum(updatableMuseum);
  }

  @PutMapping(consumes = "application/json", value = "/admin")
  @ResponseStatus(HttpStatus.OK)
  AnswerModel updateMuseumByAdmin(@RequestBody UpdatableMuseumAdmin updatableMuseum) {
    return museumFacade.updateMuseumByAdmin(updatableMuseum);
  }

  @PutMapping(value = "lock/{id}")
  @ResponseStatus(HttpStatus.OK)
  AnswerModel lockMuseum(@PathVariable Integer id) {
    return museumFacade.lockMuseum(id);
  }

  @DeleteMapping(value = "{id}")
  @ResponseStatus(HttpStatus.OK)
  AnswerModel deleteMuseum(@PathVariable Integer id) {
    return museumFacade.deleteMuseum(id);
  }

  @GetMapping
  List<ExistingMuseum> getAllMuseums() {
    return museumFacade.getAllMuseums();
  }

  @GetMapping(value = "/active")
  List<ExistingMuseum> getAllActiveMuseums() {
    return museumFacade.getAllActiveMuseums();
  }

  @GetMapping(value = "{id}")
  ExistingMuseum getMuseumById(@PathVariable Integer id) {
    return museumFacade.getMuseumById(id);
  }

  @GetMapping(value = "owner/{id}")
  AnswerModel getOwnerByMuseumId(@PathVariable Integer id) {
    return museumFacade.getOwnerByMuseumId(id);
  }
}
