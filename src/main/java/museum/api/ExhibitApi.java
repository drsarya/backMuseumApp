package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.facade.ExhibitFacade;
import service.facade.ExhibitionFacade;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

@RestController
@RequestMapping(
  value = "/exhibits",
  produces = "application/json",
  consumes = "application/json"
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

  @GetMapping(value = "/{museumId}")
  List<ExistingExhibit> getExhibitsByMuseumId(@PathVariable Integer museumId) {
    return exhibitFacade.getExhibitsByMuseumId(museumId);
  }

  @PostMapping()
  ExistingExhibit createExhibit(@RequestBody BaseExhibit exhibit) {
    return exhibitFacade.createExhibit(exhibit);
  }

  @DeleteMapping(value = "/{id}")
  boolean deleteExhibit(@PathVariable Integer id) {
    return exhibitFacade.deleteExhibit(id);
  }

  @PutMapping
  ExistingExhibit updateExhibit(@RequestBody ExistingExhibit exhibit) {
    return exhibitFacade.updateExhibit(exhibit);
  }
}
