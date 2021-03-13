package service.internal.impl;

import museum.mapper.ExhibitionMapper;
import museum.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.internal.LikeService;
import service.mapper.ExhibitionStruct;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

import java.util.List;

public class LikeServiceImpl implements LikeService {
  private final LikeMapper likeMapper;

  @Autowired
  public LikeServiceImpl(final  LikeMapper likeMapper, final ExhibitionMapper exhibitionMapper) {
    this.likeMapper = likeMapper;
   }
  @Override
  public Integer getLikesByArtId(BaseLike baseLike) {
    return null;
  }

  @Override
  public ExistingLike getLikeByUser(UserLike userLike) {
    return null;
  }

  @Override
  public boolean deleteLikeByUser(UserLike userLike) {
    return false;
  }

  @Override
  public boolean createLike(UserLike userLike) {
    return false;
  }

  @Override
  public List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser) {
    return null;
  }

  @Override
  public List<ExistingExhibition> getLikedExhibitionsByUser(Integer idUser) {
    return null;
  }
}
