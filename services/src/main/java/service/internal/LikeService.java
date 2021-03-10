package service.internal;

import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

import java.util.List;

public interface LikeService {
  Integer getLikesByArtId(BaseLike baseLike);

  ExistingLike getLikeByUser(UserLike userLike);

  boolean deleteLikeByUser(UserLike userLike);

  boolean createLike(UserLike userLike);

  List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser);

  List<ExistingExhibition>  getLikedExhibitionsByUser(Integer idUser);
}
