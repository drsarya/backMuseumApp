package service.facade;

import org.springframework.validation.annotation.Validated;
import service.model.OkModel;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

import java.util.List;
@Validated
public interface LikeFacade {
  OkModel getLikesByArtId(BaseLike baseLike);

  ExistingLike getLikeByUser(UserLike userLike);


  OkModel createLike(UserLike userLike);

  List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser);

  List<ExistingExhibition>  getLikedExhibitionsByUser(Integer idUser);
}
