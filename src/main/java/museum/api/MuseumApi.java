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

@RestController
@RequestMapping(
  value = "/museum",
  produces = "application/json"
)
public class MuseumApi {

  private final MuseumFacade museumFacade;
  private static final Logger logger = LoggerFactory.getLogger(MuseumApi.class);

  @Autowired
  public MuseumApi(final  MuseumFacade museumFacade) {
    this.museumFacade = museumFacade;
  }


  @PostMapping( consumes = "application/json" )
  @ResponseStatus(HttpStatus.CREATED)
  ExistingMuseum createMuseum(@RequestBody final BaseMuseum baseMuseum) throws Exception {
    logger.info("Consumed: {}", baseMuseum);
    return museumFacade.createMuseum(baseMuseum);
  }

  @PostMapping(   consumes={ "multipart/form-data"},value = "/upload")
  @ResponseStatus(HttpStatus.CREATED)
  //ModelAttribute
  ExistingMuseum updateMuseum(@ModelAttribute    UpdatableMuseum updatableMuseum,  @RequestPart("imageUpload") MultipartFile upload) throws Exception {

   // MultipartFile ss = updatableMuseum.getImage();

    logger.info("Consumed: {}", updatableMuseum);
    return museumFacade.updateMuseum(updatableMuseum,  upload );
  }
}
