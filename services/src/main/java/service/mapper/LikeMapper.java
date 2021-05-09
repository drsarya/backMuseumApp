package service.mapper;

import museum.domen.LikeModel;
import museum.domen.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.like.ExistingLike;
import service.model.like.UserLike;

@Mapper
public interface LikeMapper {
  @Mapping(target = "type", source = "likeModel.type")
  @Mapping(target = "artId", source = "likeModel.artId")
  @Mapping(target = "id", source = "likeModel.id")
  ExistingLike toExistingLike(LikeModel likeModel);


  @Mapping(target = "type", source = "like.type")
  @Mapping(target = "artId", source = "like.artId")
  @Mapping(target = "user.id", source = "like.userId")
  LikeModel toLikeModel(UserLike like );

}
