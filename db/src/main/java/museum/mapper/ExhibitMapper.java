package museum.mapper;

import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.domen.MuseumModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import src.model.MuseumStateEnum;

import java.util.List;

public interface ExhibitMapper  extends CrudRepository<ExhibitModel, Long> {


 //getExhibitsByMuseumId
  List<ExhibitModel> findExhibitModelsByExhibition_Museum_Id(Integer id);
  List<ExhibitModel> findExhibitModelsByExhibition_Id(Integer id);
  List<ExhibitModel> findExhibitModelsByExhibition_Museum_State(MuseumStateEnum museumStateEnum);

//  void findExhibitionModelsByMuseum_Id(Integer id);
}
