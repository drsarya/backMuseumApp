package service.internal.impl;

import museum.domen.LikeModel;
import museum.domen.UserModel;
import museum.mapper.LikeMapper;
import museum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.LikeService;
import service.mapper.*;
import service.model.AnswerModel;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;
import validation.ValidationErrorTerms;

import javax.transaction.Transactional;

@Service
public class LikeServiceImpl implements LikeService {
  private final LikeMapper likeMapper;
  private final LikeStruct likeStruct;
  private final UserMapper userMapper;

  @Autowired
  public LikeServiceImpl(final LikeMapper likeMapper, final LikeStruct likeStruct,
                         final UserMapper userMapper) {
    this.likeMapper = likeMapper;
    this.likeStruct = likeStruct;
    this.userMapper = userMapper;
  }

  @Override
  public AnswerModel getCountOfLikesByArtId(BaseLike baseLike) {
    Integer count = likeMapper.countAllByArtIdAndType(baseLike.getArtId(), baseLike.getType());
    if (count != null) {
      return new AnswerModel(Integer.toString(count));
    }
    throw new IllegalArgumentException(ValidationErrorTerms.OBJECT_NOT_FOUND);
  }

  @Override
  public ExistingLike getLikeByUser(UserLike userLike) {
    LikeModel likeModel = likeMapper.findLikeModelByUser_IdAndTypeAndArtId(userLike.getUserId(), userLike.getType(), userLike.getArtId());
    if (likeModel != null) {
      return likeStruct.toExistingLike(likeModel);
    }
    return null;
  }

  @Transactional
  @Override
  public AnswerModel createLike(UserLike userLike) {
    AnswerModel answerModel;
    ExistingLike existingLike = getLikeByUser(userLike);
    if (existingLike != null) {
      answerModel = new AnswerModel("Успешное удаление");
      likeMapper.deleteById(existingLike.getId());
    } else {
      UserModel userModel = userMapper.findById(userLike.getUserId());
      if (userModel != null) {
        LikeModel like = likeStruct.toLikeModel(userLike, userModel);
        likeMapper.save(like);
        answerModel = new AnswerModel("Успешное добавление");
      } else throw new IllegalArgumentException(ValidationErrorTerms.USER_NOT_FOUND);
    }
    return answerModel;
  }
}
