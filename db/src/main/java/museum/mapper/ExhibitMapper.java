package museum.mapper;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.domen.MuseumModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import src.model.MuseumStateEnum;

import java.util.List;

public interface ExhibitMapper extends CrudRepository<ExhibitModel, Integer> {
  ExhibitModel findById(Integer id);

  List<ExhibitModel> findExhibitModelsByExhibition_Museum_Id(Integer id);

  List<ExhibitModel> findExhibitModelsByExhibition_Id(Integer id);

  List<ExhibitModel> findExhibitModelsByExhibition_Museum_State(MuseumStateEnum museumStateEnum);

  @Query(" SELECT   e1  FROM  likes as  l1  JOIN exhibits as e1 ON e1.id = l1.artId  WHERE l1.user.id  = :userId  and l1.type ='EXHIBIT'")
  List<ExhibitModel> getLikedExhibitsByUser(@Param("userId") Integer userId);

}
