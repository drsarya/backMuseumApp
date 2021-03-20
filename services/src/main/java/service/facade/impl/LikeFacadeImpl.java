package service.facade.impl;

import org.springframework.stereotype.Service;
import service.facade.LikeFacade;
import service.internal.ExhibitionService;
import service.internal.LikeService;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

import java.util.List;
@Service
public class LikeFacadeImpl  implements LikeFacade {
  private final LikeService likeService;

  public LikeFacadeImpl(final LikeService likeService) {
    this.likeService = likeService;
  }

  @Override
  public Integer getLikesByArtId(BaseLike baseLike) {
    return likeService.getLikesByArtId(baseLike);
  }

  @Override
  public ExistingLike getLikeByUser(UserLike userLike) {
    return likeService.getLikeByUser(userLike);
  }

  @Override
  public boolean deleteLikeByUser(UserLike userLike) {
    return likeService.deleteLikeByUser(userLike);
  }

  @Override
  public boolean createLike(UserLike userLike) {
    return likeService.createLike(userLike);
  }

  @Override
  public List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser) {
    return likeService.getLikedExhibitsByUser(idUser);
  }

  @Override
  public List<ExistingExhibition> getLikedExhibitionsByUser(Integer idUser) {
    return likeService.getLikedExhibitionsByUser(idUser);
  }
}
