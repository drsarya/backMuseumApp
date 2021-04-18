package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.LikeFacade;
import service.internal.LikeService;
import service.model.OkModel;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

import java.util.List;

@Service
public class LikeFacadeImpl implements LikeFacade {
  private final LikeService likeService;


  public LikeFacadeImpl(final LikeService likeService) {
    this.likeService = likeService;
  }

  @Override
  public OkModel getLikesByArtId(BaseLike baseLike) {
    return likeService.getCountOfLikesByArtId(baseLike);
  }

  @Override
  public ExistingLike getLikeByUser(UserLike userLike) {
    return likeService.getLikeByUser(userLike);
  }


  @Override
  public OkModel createLike(UserLike userLike) {
    return likeService.createLike(userLike);
  }


}
