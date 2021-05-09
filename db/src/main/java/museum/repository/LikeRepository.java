package museum.repository;

import museum.domen.LikeModel;
import org.springframework.data.repository.CrudRepository;
import src.model.TypeOfArtEnum;

public interface LikeRepository extends CrudRepository<LikeModel, Long> {

  Integer countAllByArtIdAndType(Integer idArt, TypeOfArtEnum typeOfArtEnum);

  LikeModel findLikeModelByUser_IdAndTypeAndArtId(Integer userId, TypeOfArtEnum typeOfArtEnum, Integer artId);

  void deleteById(Integer id);

  void deleteByArtIdAndAndType(Integer idArt, TypeOfArtEnum typeOfArtEnum);

}


