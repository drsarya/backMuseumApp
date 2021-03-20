package museum.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.facade.UserFacade;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
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


  @PostMapping( consumes = "application/json" )
  ExistingUser createUser(@RequestBody final NewUser user) throws Exception {
    return userFacade.createUser(user);
  }


  @PostMapping( consumes = "application/json", value = "/get")
  ExistingUser getUser(@RequestBody final NewUser user) throws Exception {
    if (user != null) {
      return userFacade.getUser(user);
    } else {
      return null;
    }
  }


//  @PostMapping( consumes = "multipart/form-data", value = "/upload")
//  @ResponseStatus(HttpStatus.CREATED)
//  void getUser(@RequestBody final File file) throws Exception {
//    logger.info("Consumed: {}", file);
//    byte[]  arr = Files.readAllBytes(file.toPath());
//
//  }

  @PutMapping( consumes = "application/json")
  Boolean updatePassword(@RequestBody final UserUpdate user) throws Exception {
    if (user != null) {
      return userFacade.updateUserPassword(user);
    } else {
      return false;
    }
  }


}
