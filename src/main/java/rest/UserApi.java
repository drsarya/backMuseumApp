package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.facade.UserFacade;
import service.model.ExistingUser;
import service.model.NewUser;
import service.model.UserUpdate;

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
  @ResponseStatus(HttpStatus.CREATED)
  ExistingUser createUser(@RequestBody final NewUser user) throws Exception {
    logger.info("Consumed: {}", user);
    return userFacade.createUser(user);
  }


  @PostMapping( consumes = "application/json", value = "/get")
  @ResponseStatus(HttpStatus.CREATED)
  ExistingUser getUser(@RequestBody final NewUser user) throws Exception {
    logger.info("Consumed: {}", user);
    if (user != null) {
      return userFacade.getUser(user);
    } else {
      return null;
    }
  }


  @PutMapping( consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  Boolean updatePassword(@RequestBody final UserUpdate user) throws Exception {
    logger.info("Consumed: {}", user);
    if (user != null) {
      return userFacade.updateUserPassword(user);
    } else {
      return false;
    }
  }

  @GetMapping
  @RequestMapping(
    value = "/{login}"
  )
  @ResponseStatus(HttpStatus.CREATED)
  ExistingUser getUserMuseum(@PathVariable("login") String login) {
    logger.info("Consumed: {}", login);
    if (login != null) {
      return userFacade.getUserMuseum(login);
    } else {
      return null;
    }
  }

}
