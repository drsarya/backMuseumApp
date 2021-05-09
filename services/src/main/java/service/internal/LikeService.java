package service.internal;

import service.model.AnswerModel;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;
import src.model.TypeOfArtEnum;

public interface LikeService {
  AnswerModel getCountOfLikesByArtId(BaseLike baseLike);

  ExistingLike getLikeByUser(UserLike userLike);

  AnswerModel createLike(UserLike userLike);

  void deleteArts(Integer artId, TypeOfArtEnum artEnum);
}
