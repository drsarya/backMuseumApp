package service.internal;

import service.model.AnswerModel;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

public interface LikeService {
  AnswerModel getCountOfLikesByArtId(BaseLike baseLike);

  ExistingLike getLikeByUser(UserLike userLike);

  AnswerModel createLike(UserLike userLike);

}
