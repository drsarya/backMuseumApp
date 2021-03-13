package museum.mapper;

import museum.domen.ExhibitionModel;
import museum.domen.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExhibitionMapper  extends CrudRepository<ExhibitionModel, Long> {



  //void findExhibitionModelByMuseum_Login(String login);

  List<ExhibitionModel> findExhibitionModelsByMuseumId(int id);

}
