package service.internal.impl;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.domen.LikeModel;
import museum.domen.UserModel;
import museum.mapper.LikeMapper;
import museum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.LikeService;
import service.mapper.*;
import service.model.OkModel;
import service.model.author.ExistingAuthor;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;
import service.model.museum.ExistingMuseum;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
  private final LikeMapper likeMapper;
  private final LikeStruct likeStruct;
  private final MuseumStruct museumStruct;
  private final UserMapper userMapper;

  private final ExhibitStruct exhibitStruct;
  private final ExhibitionStruct exhibitionStruct;
  private final AuthorStruct authorStruct;

  @Autowired
  public LikeServiceImpl(final LikeMapper likeMapper, final LikeStruct likeStruct, final AuthorStruct authorStruct,
                         final UserMapper userMapper, final ExhibitStruct exhibitStruct, final MuseumStruct museumStruct, final ExhibitionStruct exhibitionStruct) {
    this.likeMapper = likeMapper;
    this.likeStruct = likeStruct;
    this.userMapper = userMapper;
    this.museumStruct = museumStruct;

    this.exhibitStruct = exhibitStruct;
    this.authorStruct = authorStruct;
    this.exhibitionStruct = exhibitionStruct;
  }

  @Override
  public OkModel getCountOfLikesByArtId(BaseLike baseLike) {
    Integer count = likeMapper.countAllByArtIdAndType(baseLike.getArtId(), baseLike.getType());
    if (count != null) {
      return new OkModel(Integer.toString(count));
    }
    throw new IllegalArgumentException("Лайки не найдены");
  }

  @Override
  public ExistingLike getLikeByUser(UserLike userLike) {
    LikeModel likeModel = likeMapper.findLikeModelByUser_IdAndTypeAndArtId(userLike.getUserId(), userLike.getType(), userLike.getArtId());
    if (likeModel != null) {
      return likeStruct.toExistingLike(likeModel);
    }
    return null;
  }


  @Override
  public OkModel createLike(UserLike userLike) {
    OkModel okModel;
    ExistingLike existingLike = getLikeByUser(userLike);
    if (existingLike != null) {
      okModel = new OkModel("Успешное удаление");
      likeMapper.delete((long) existingLike.getId());
    } else {
      UserModel userModel = userMapper.findById(userLike.getUserId());
      if (userModel != null) {
        LikeModel like = likeStruct.toLikeModel(userLike, userModel);
        likeMapper.save(like);
        okModel = new OkModel("Успешное добавление");

      } else throw new IllegalArgumentException("Пользователь не найден");
    }
    return okModel;
  }




}
