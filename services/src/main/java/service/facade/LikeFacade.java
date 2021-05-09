package service.facade;

import org.springframework.validation.annotation.Validated;
import service.model.AnswerModel;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

@Validated
public interface LikeFacade {
  AnswerModel getLikesByArtId(BaseLike baseLike);

  ExistingLike getLikeByUser(UserLike userLike);

  AnswerModel createLike(UserLike userLike);

}
