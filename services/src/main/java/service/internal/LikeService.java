package service.internal;

import service.model.OkModel;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

import java.util.List;

public interface LikeService {
  Integer getCountOfLikesByArtId(BaseLike baseLike);

  ExistingLike getLikeByUser(UserLike userLike);


  OkModel createLike(UserLike userLike);

  List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser);

  List<ExistingExhibition>  getLikedExhibitionsByUser(Integer idUser);
}
