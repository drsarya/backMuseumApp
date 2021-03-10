package museum.mapper;

import museum.domen.AuthorModel;
import museum.domen.MuseumModel;
import org.springframework.data.repository.CrudRepository;

public  interface  MuseumMapper extends CrudRepository<MuseumModel, Long> {

  MuseumModel findMuseumModelByLogin(String login);

  MuseumModel findMuseumModelByLoginAndId(String login, int id);
MuseumModel findById(Long id);

}
