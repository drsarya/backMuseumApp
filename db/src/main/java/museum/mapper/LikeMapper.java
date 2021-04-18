package museum.mapper;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.domen.LikeModel;
import museum.domen.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import src.model.TypeOfArtEnum;

import java.util.List;

public interface LikeMapper extends CrudRepository<LikeModel, Long> {
  LikeModel findById(Integer id);

  Integer countAllByArtIdAndType(   Integer idArt, TypeOfArtEnum typeOfArtEnum);

  LikeModel findLikeModelByUser_IdAndTypeAndArtId(Integer userId, TypeOfArtEnum typeOfArtEnum, Integer artId);





}


