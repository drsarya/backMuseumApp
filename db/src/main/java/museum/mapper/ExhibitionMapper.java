package museum.mapper;

import museum.domen.ExhibitionModel;
import museum.domen.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface ExhibitionMapper  extends CrudRepository<ExhibitionModel, Long> {



  void findExhibitionModelByMuseum_Login(String login);

  void findExhibitionModelByMuseumId(int id);

}
