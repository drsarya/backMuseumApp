package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.facade.UserFacade;
import service.model.AnswerModel;
import service.model.user.ExistingUser;
import service.model.user.NewUser;
import service.model.user.UserMuseum;
import service.model.user.UserUpdate;

@RestController
@RequestMapping(
  value = "/users",
  produces = "application/json"
)
public class UserApi {
  private final UserFacade userFacade;

  @Autowired
  public UserApi(final UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  @PostMapping(consumes = "application/json")
  AnswerModel createUser(@RequestBody final NewUser user) throws Exception {
    return userFacade.createUser(user);
  }

  @PostMapping(consumes = "application/json", value = "/get")
  ExistingUser getUser(@RequestBody final NewUser user) throws Exception {
    return userFacade.getUser(user);
  }

  @PutMapping(consumes = "application/json", value = "/museum")
  AnswerModel updateMuseumUserPass(@RequestBody UserMuseum userMuseum) throws Exception {
    return userFacade.updateMuseumUserPass(userMuseum);
  }

  @PutMapping(consumes = "application/json")
  AnswerModel updatePassword(@RequestBody final UserUpdate user) throws Exception {
    return userFacade.updateUserPassword(user);
  }
}
