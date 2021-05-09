package museum.repository;

import museum.domen.MuseumModel;
import org.springframework.data.repository.CrudRepository;

public interface MuseumRepository extends CrudRepository<MuseumModel, Integer> {

  MuseumModel findById(Integer id);

}
