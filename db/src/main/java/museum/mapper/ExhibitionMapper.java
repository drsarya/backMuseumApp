package museum.mapper;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.domen.MuseumModel;
import museum.domen.UserModel;
import org.springframework.data.repository.CrudRepository;
import src.model.MuseumStateEnum;

import java.util.List;

public interface ExhibitionMapper  extends CrudRepository<ExhibitionModel, Long> {


  ExhibitionModel findById(Integer id);
  //void findExhibitionModelByMuseum_Login(String login);
  List<ExhibitionModel> findExhibitionModelsByMuseumState(MuseumStateEnum museumStateEnum);

  List<ExhibitionModel> findExhibitionModelsByMuseumId(Integer id);

}
