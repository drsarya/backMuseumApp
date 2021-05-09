package service.internal.impl;

import museum.domen.LikeModel;
import museum.domen.UserModel;
import museum.repository.LikeRepository;
import museum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.LikeService;
import service.internal.UserService;
import service.mapper.*;
import service.model.AnswerModel;
import service.model.like.BaseLike;
import service.model.like.ExistingLike;
import service.model.like.UserLike;
import service.model.user.ExistingUser;
import src.model.TypeOfArtEnum;
import validation.ValidationErrorTerms;

import javax.transaction.Transactional;

@Service
public class LikeServiceImpl implements LikeService {
  private final LikeRepository likeRepository;
  private final LikeMapper likeMapper;
  private final UserService userService;

  @Autowired
  public LikeServiceImpl(final LikeRepository likeRepository, final LikeMapper likeMapper,
                         UserService userService) {
    this.likeRepository = likeRepository;
    this.likeMapper = likeMapper;
    this.userService = userService;
  }

  @Override
  public AnswerModel getCountOfLikesByArtId(BaseLike baseLike) {
    Integer count = likeRepository.countAllByArtIdAndType(baseLike.getArtId(), baseLike.getType());
    if (count != null) {
      return new AnswerModel(Integer.toString(count));
    }
    throw new IllegalArgumentException(ValidationErrorTerms.OBJECT_NOT_FOUND);
  }

  @Override
  public ExistingLike getLikeByUser(UserLike userLike) {
    LikeModel likeModel = likeRepository.findLikeModelByUser_IdAndTypeAndArtId(userLike.getUserId(), userLike.getType(), userLike.getArtId());
    if (likeModel != null) {
      return likeMapper.toExistingLike(likeModel);
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
      likeRepository.deleteById(existingLike.getId());
    } else {
      ExistingUser userModel = userService.getById(userLike.getUserId());
      if (userModel != null) {
        LikeModel like = likeMapper.toLikeModel(userLike);
        likeRepository.save(like);
        answerModel = new AnswerModel("Успешное добавление");
      } else throw new IllegalArgumentException(ValidationErrorTerms.USER_NOT_FOUND);
    }
    return answerModel;
  }

  @Override
  public void deleteArts(Integer artId, TypeOfArtEnum artEnum) {
    likeRepository.deleteByArtIdAndAndType(artId, artEnum);
  }
}
