package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.facade.LikeFacade;
import service.model.OkModel;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

import java.util.List;

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
  OkModel getLikesByArtId(@RequestBody BaseLike baseLike) {
    return likeFacade.getLikesByArtId(baseLike);
  }

  @PostMapping(value = "/user", consumes = "application/json")
  ExistingLike getLikeByUser(@RequestBody UserLike userLike) {
    return likeFacade.getLikeByUser(userLike);
  }


  @PostMapping(consumes = "application/json")
  OkModel createLike(@RequestBody UserLike userLike) {
    return likeFacade.createLike(userLike);
  }




}
