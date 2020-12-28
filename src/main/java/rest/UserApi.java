package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.facade.UserFacade;
import service.model.ExistingUser;
import service.model.NewUser;

@RestController
@RequestMapping(
  value = "/users",
  consumes = "application/json",
  produces = "application/json"
)
public class UserApi {

  private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

  private final UserFacade userFacade;

  @Autowired
  public UserApi(final UserFacade userFacade) {
    this.userFacade = userFacade;
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ExistingUser createUser(@RequestBody final NewUser user) throws Exception {
    logger.info("Consumed: {}", user);

    return userFacade.createUser(user);
  }


  @GetMapping
  @ResponseStatus(HttpStatus.CREATED)
  ExistingUser getUser(@RequestBody final NewUser user) throws Exception {
    logger.info("Consumed: {}", user);
    if (userFacade.getUser(user) != null) {
      return userFacade.getUser(user);
    } else {
      return null;
    }

  }
}
