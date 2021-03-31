package museum.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.facade.MuseumFacade;
import service.facade.UserFacade;
import service.model.OkModel;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;

import java.io.File;
import java.nio.file.Files;

@RestController
@RequestMapping(
  value = "/users",
  produces = "application/json"
)
public class UserApi {

  private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

  private final UserFacade userFacade;

  @Autowired
  public UserApi(final UserFacade userFacade) {
    this.userFacade = userFacade;

  }
  @PostMapping(consumes = "application/json")
  OkModel createUser(@RequestBody final NewUser user) throws Exception {

    return userFacade.createUser(user);
  }


  @PostMapping(consumes = "application/json", value = "/get")
  ExistingUser getUser(@RequestBody final NewUser user) throws Exception {
    if (user != null) {
      return userFacade.getUser(user);
    } else {
      return null;
    }
  }

  @PutMapping(consumes = "application/json", value = "/museum")
  OkModel updateMuseumUserPass(UserMuseum userMuseum) throws Exception {
    return userFacade.updateMuseumUserPass(userMuseum);
  }

  @PutMapping(consumes = "application/json")
  OkModel updatePassword(@RequestBody final UserUpdate user) throws Exception {

      return userFacade.updateUserPassword(user);

  }

}
