package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.facade.LikeFacade;
import service.model.AnswerModel;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

@RestController
@RequestMapping(
  value = "/likes",
  produces = "application/json"

)
public class LikeApi {
  private final LikeFacade likeFacade;

  @Autowired
  public LikeApi(final LikeFacade likeFacade) {
    this.likeFacade = likeFacade;
  }

  @PostMapping(value = "/count", consumes = "application/json")
  AnswerModel getLikesByArtId(@RequestBody BaseLike baseLike) {
    return likeFacade.getLikesByArtId(baseLike);
  }

  @PostMapping(value = "/user", consumes = "application/json")
  ExistingLike getLikeByUser(@RequestBody UserLike userLike) {
    return likeFacade.getLikeByUser(userLike);
  }

  @PostMapping(consumes = "application/json")
  AnswerModel createLike(@RequestBody UserLike userLike) {
    return likeFacade.createLike(userLike);
  }

}
