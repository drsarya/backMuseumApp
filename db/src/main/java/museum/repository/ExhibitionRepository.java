package museum.repository;

import museum.domen.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import src.model.MuseumStateEnum;

import java.util.List;

public interface ExhibitionRepository extends CrudRepository<ExhibitionModel, Integer> {

  ExhibitionModel findById(Integer id);

  List<ExhibitionModel> findExhibitionModelsByMuseumState(MuseumStateEnum museumStateEnum);

  List<ExhibitionModel> findExhibitionModelsByMuseumId(Integer id);

  @Query(" SELECT   e1  FROM  likes as  l  " +
    " JOIN exhibitions as e1 ON e1.id = l.artId " +
    "  WHERE l.user.id = :userId and l.type = 'EXHIBITION' and e1.museum.state =  'ACTIVE'  ")
  List<ExhibitionModel> getLikedExhibitionsByUser(@Param("userId") Integer userId);

}
