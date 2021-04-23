package museum.mapper;

import museum.domen.LikeModel;
import org.springframework.data.repository.CrudRepository;
import src.model.TypeOfArtEnum;

public interface LikeMapper extends CrudRepository<LikeModel, Long> {

  Integer countAllByArtIdAndType(Integer idArt, TypeOfArtEnum typeOfArtEnum);

  LikeModel findLikeModelByUser_IdAndTypeAndArtId(Integer userId, TypeOfArtEnum typeOfArtEnum, Integer artId);

  void deleteById(Integer id);

}


