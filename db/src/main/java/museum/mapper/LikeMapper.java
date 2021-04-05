package museum.mapper;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.domen.LikeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import src.model.TypeOfArtEnum;

import java.util.List;

public interface LikeMapper extends CrudRepository<LikeModel, Long> {


  Integer countAllByArtIdAndType(   Integer idArt, TypeOfArtEnum typeOfArtEnum);

  LikeModel findLikeModelByUser_IdAndTypeAndArtId(Integer userId, TypeOfArtEnum typeOfArtEnum, Integer artId);

  //and type = exhibit
  @Query(" SELECT   e1  FROM  likes as  l1  JOIN exhibits as e1 ON e1.id = l1.id  WHERE l1.user  = :userId and l1.type = exhibit ")
  List<ExhibitModel> getLikedExhibitsByUser(Integer userId);


  @Query(" SELECT   l  FROM  likes as  l   JOIN exhibitions as e1 ON e1.id = l.id   WHERE l.user = :userId and l.type = exhibition  ")
  List<ExhibitionModel> getLikedExhibitionsByUser(Integer userId);

}


