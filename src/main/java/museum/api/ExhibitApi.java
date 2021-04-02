package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.facade.ExhibitFacade;
import service.facade.ExhibitionFacade;
import service.model.OkModel;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

@RestController
@RequestMapping(
  value = "/exhibits",
  produces = "application/json"
)
public class ExhibitApi {


  private final ExhibitFacade exhibitFacade;

  @Autowired
  public ExhibitApi(final ExhibitFacade exhibitFacade) {
    this.exhibitFacade = exhibitFacade;
  }

  @GetMapping
  List<ExistingExhibit> getAllExhibits() {
    return exhibitFacade.getAllExhibits();
  }

  @GetMapping(value = "/{museumId}", consumes = "application/json")
  List<ExistingExhibit> getExhibitsByExhibitionId(@PathVariable Integer museumId) {
    return exhibitFacade.getExhibitsByExhibitionId(museumId);
  }

  @GetMapping(value = "/exhibition/{exhibitionId}", consumes = "application/json")
  List<ExistingExhibit> getExhibitsByMuseumId(@PathVariable Integer exhibitionId) {
    return exhibitFacade.getExhibitsByMuseumId(exhibitionId);
  }
  @PostMapping(consumes = "application/json")
  ExistingExhibit createExhibit(@RequestBody BaseExhibit exhibit) {
    return exhibitFacade.createExhibit(exhibit);
  }

  @DeleteMapping(value = "/{id}", consumes = "application/json")
  OkModel  deleteExhibit(@PathVariable Integer id) {
    return exhibitFacade.deleteExhibit(id);
  }

  @PutMapping(consumes = "application/json")
  ExistingExhibit updateExhibit(@RequestBody ExistingExhibit exhibit) {
    return exhibitFacade.updateExhibit(exhibit);
  }
}
