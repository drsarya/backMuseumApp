package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.facade.ExhibitFacade;
import service.facade.ExhibitionFacade;
import service.model.OkModel;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;

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

  @GetMapping(value = "/exhibition/{exhibitionId}")
  List<ExistingExhibit> getExhibitsByExhibitionId(@PathVariable Integer exhibitionId) {
    return exhibitFacade.getExhibitsByExhibitionId(exhibitionId);
  }

  @GetMapping(value = "/museumId/{museumId}")
  List<ExistingExhibit> getExhibitsByMuseumId(@PathVariable Integer museumId) {
    return exhibitFacade.getExhibitsByMuseumId(museumId);
  }

  @PostMapping(consumes = "application/json")
  ExistingExhibit createExhibit(@RequestBody BaseExhibit exhibit) {
    return exhibitFacade.createExhibit(exhibit);
  }

  @DeleteMapping(value = "/{id}")
  OkModel deleteExhibit(@PathVariable Integer id) {
    return exhibitFacade.deleteExhibit(id);
  }

  @GetMapping(value = "/liked/{idUser}")
  List<ExistingExhibit> getLikedExhibitsByUser(@PathVariable("idUser") Integer idUser) {
    return exhibitFacade.getLikedExhibitsByUser(idUser);
  }

  @PutMapping(consumes = {"multipart/form-data", "application/json"})
  ExistingExhibit updateExhibit(@RequestPart("imageUpload") MultipartFile upload, @RequestPart("exhibit") ExistingExhibit exhibit) {
    return exhibitFacade.updateExhibit(upload, exhibit);
  }

}
