package museum.mapper;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExhibitMapper  extends CrudRepository<ExhibitionModel, Long> {


  List<ExhibitModel> findExhibitionModelsByMuseum_Login(String login);

  void findExhibitionModelsByMuseum_Id(Integer id);
}
