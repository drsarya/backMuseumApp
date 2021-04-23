package museum.mapper;

import museum.domen.MuseumModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MuseumMapper extends CrudRepository<MuseumModel, Integer> {

  MuseumModel findById(Integer id);

}
