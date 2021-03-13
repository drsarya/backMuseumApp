package museum.mapper;

import museum.domen.ExhibitModel;
import museum.domen.LikeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import src.model.TypeOfArtEnum;

import java.util.List;

public interface LikeMapper extends CrudRepository<LikeModel, Long> {


  /*


    List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser);

    List<ExistingExhibition>  getLikedExhibitionsByUser(Integer idUser);*/
  List<LikeModel> findLikeModelsByArtIdAndAndType(Integer id, TypeOfArtEnum typeOfArtEnum);

  List<LikeModel> findLikeModelByArtIdAndTypeAndUser_Id(Integer idArt, TypeOfArtEnum typeOfArtEnum, Integer idUser);

 // @Query(" SELECT   l1  FROM  likes   l1  JOIN exhibits as e1 ON e1.id = l1.id  JOIN authors as a1 On a1.id = e1.authorId  WHERE l1.id_user_fk = :userId AND l1.type = :type  ")
//  List<ExhibitModel> getLikedExhibitsByUser(Integer userId);

}
