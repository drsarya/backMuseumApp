package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.LikeFacade;
import service.internal.LikeService;
import service.model.AnswerModel;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

@Service
public class LikeFacadeImpl implements LikeFacade {
  private final LikeService likeService;

  public LikeFacadeImpl(final LikeService likeService) {
    this.likeService = likeService;
  }

  @Override
  public AnswerModel getLikesByArtId(BaseLike baseLike) {
    return likeService.getCountOfLikesByArtId(baseLike);
  }

  @Override
  public ExistingLike getLikeByUser(UserLike userLike) {
    return likeService.getLikeByUser(userLike);
  }

  @Override
  public AnswerModel createLike(UserLike userLike) {
    return likeService.createLike(userLike);
  }

}
