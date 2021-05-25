package museum.repository;

import museum.domen.ExhibitModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import src.model.MuseumStateEnum;

import java.util.List;

public interface ExhibitRepository extends CrudRepository<ExhibitModel, Integer> {
  ExhibitModel findById(Integer id);

  List<ExhibitModel> findExhibitModelsByExhibition_Museum_Id(Integer id);

  List<ExhibitModel> findExhibitModelsByExhibition_Id(Integer id);

  List<ExhibitModel> findExhibitModelsByExhibition_Museum_State(MuseumStateEnum museumStateEnum);

  @Query(" SELECT   e1  FROM  likes as  l1 " +
    "   JOIN exhibits as e1 ON e1.id = l1.artId " +
    "    JOIN exhibitions as e2 ON e2.id = e1.exhibition.id " +
    "     JOIN museums as m1 ON m1.id = e2.museum.id " +
    "     WHERE l1.user.id  = :userId  and l1.type ='EXHIBIT' and m1.state = 'ACTIVE'")
  List<ExhibitModel> getLikedExhibitsByUser(@Param("userId") Integer userId);

}
