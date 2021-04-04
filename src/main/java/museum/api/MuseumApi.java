package museum.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.facade.MuseumFacade;
import service.model.OkModel;
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
  private static final Logger logger = LoggerFactory.getLogger(MuseumApi.class);

  @Autowired
  public MuseumApi(final MuseumFacade museumFacade) {
    this.museumFacade = museumFacade;
  }

  @PostMapping(value = "/{login}", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  OkModel createMuseum(@RequestBody  final BaseMuseum baseMuseum, @PathVariable String login) {
    return museumFacade.createMuseum(baseMuseum, login);
  }

  @PutMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  OkModel updateMuseum(@RequestBody UpdatableMuseum updatableMuseum)  {
    return museumFacade.updateMuseum(updatableMuseum);
  }
  @PutMapping(consumes = "application/json", value = "/admin")
  @ResponseStatus(HttpStatus.OK)
  OkModel updateMuseumByAdmin(@RequestBody UpdatableMuseumAdmin updatableMuseum) {
    return museumFacade.updateMuseumByAdmin(updatableMuseum);
  }
  @PutMapping(value = "lock/{id}")
  @ResponseStatus(HttpStatus.OK)
  OkModel lockMuseum(@PathVariable Integer id) {
    return museumFacade.lockMuseum(id);
  }

  @DeleteMapping( value = "{id}")
  @ResponseStatus(HttpStatus.OK)
  OkModel deleteMuseum(@PathVariable Integer id) {
    return museumFacade.deleteMuseum(id);
  }

  @GetMapping
  List<ExistingMuseum> getAllMuseums() {
    return museumFacade.getAllMuseums();
  }
  @GetMapping(value = "{id}")
   ExistingMuseum  getMuseumById(@PathVariable Integer id) {
    return museumFacade.getMuseumById(id);
  }

  @GetMapping(value = "owner/{id}" )
  OkModel getOwnerByMuseumId(@PathVariable Integer id) {
    return museumFacade.getOwnerByMuseumId(id);
  }


}
