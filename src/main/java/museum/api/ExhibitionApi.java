package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.facade.ExhibitionFacade;
import service.facade.LikeFacade;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;

@RestController
@RequestMapping(
  value = "/exhibitions",
  produces = "application/json",
  consumes = "application/json"
)
public class ExhibitionApi {

  private final ExhibitionFacade exhibitionFacade;

  @Autowired
  public ExhibitionApi(final ExhibitionFacade exhibitionFacade) {
    this.exhibitionFacade = exhibitionFacade;
  }

  @GetMapping
  List<ExistingExhibition> getAllExhibitions() {
    return exhibitionFacade.getAllExhibitions();
  }

  @PostMapping
  ExistingExhibition createExhibition(@RequestBody BaseExhibition exhibition) {
    return exhibitionFacade.createExhibition(exhibition);
  }

  @PutMapping
  ExistingExhibition updateExhibition(@RequestBody ExistingExhibition exhibition) {
    return exhibitionFacade.updateExhibition(exhibition);
  }

  @GetMapping(value = "/{id}")
  List<ExistingExhibition> getExhibitionsByMuseumId(@PathVariable int id) {
    return exhibitionFacade.getExhibitionsByMuseumId(id);
  }

  @DeleteMapping
  boolean deleteExhibition(int id) {
    return exhibitionFacade.deleteExhibition(id);
  }
}
