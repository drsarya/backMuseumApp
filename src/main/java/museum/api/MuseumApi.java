package museum.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.facade.MuseumFacade;
import service.facade.UserFacade;
import service.model.museum.BaseMuseum;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;
import service.model.user.ExistingUser;
import service.model.user.NewUser;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(
  value = "/museum",
  produces = "application/json",
  consumes = "application/json"
)
public class MuseumApi {

  private final MuseumFacade museumFacade;
  private static final Logger logger = LoggerFactory.getLogger(MuseumApi.class);

  @Autowired
  public MuseumApi(final MuseumFacade museumFacade) {
    this.museumFacade = museumFacade;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ExistingMuseum createMuseum(@RequestBody final BaseMuseum baseMuseum) {
    return museumFacade.createMuseum(baseMuseum);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  ExistingMuseum updateMuseum(@RequestBody UpdatableMuseum updatableMuseum) throws Exception {
    return museumFacade.updateMuseum(updatableMuseum);
  }

  @GetMapping
  List<ExistingMuseum> getAllMuseums() {
    return museumFacade.getAllMuseums();
  }

  @GetMapping(value = "/{id}")
  ExistingMuseum getMuseumByWorkerId(@PathVariable Integer id) {
    return museumFacade.getMuseumByWorkerId(id);
  }




}
