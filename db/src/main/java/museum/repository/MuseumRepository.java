package museum.repository;

import museum.domen.MuseumModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MuseumRepository extends CrudRepository<MuseumModel, Integer> {

  MuseumModel findById(Integer id);
  @Query(" SELECT   m1  FROM  museums as  m1   where m1.state = 'ACTIVE'")
  List<MuseumModel> findAllByState( );
}
