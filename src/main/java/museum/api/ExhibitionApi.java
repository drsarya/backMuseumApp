package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.facade.ExhibitionFacade;
import service.model.AnswerModel;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import java.util.List;

@RestController
@RequestMapping(
  value = "/exhibitions",
  produces = "application/json"
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

  @PostMapping(
    consumes = "application/json")
  ExistingExhibition createExhibition(@RequestBody BaseExhibition exhibition) {
    return exhibitionFacade.createExhibition(exhibition);
  }

  @PutMapping(
    consumes = {"multipart/form-data", "application/json"})
  ExistingExhibition updateExhibition(@RequestPart("imageUpload") MultipartFile upload, @RequestPart("exhibition") ExistingExhibition exhibition) {
    return exhibitionFacade.updateExhibition(upload, exhibition);
  }

  @GetMapping(value = "/liked/{idUser}")
  List<ExistingExhibition> getLikedExhibitionsByUser(@PathVariable("idUser") Integer idUser) {
    return exhibitionFacade.getLikedExhibitionsByUser(idUser);
  }

  @GetMapping(value = "/{id}")
  List<ExistingExhibition> getExhibitionsByMuseumId(@PathVariable Integer id) {
    return exhibitionFacade.getExhibitionsByMuseumId(id);
  }

  @DeleteMapping(value = "/{id}")
  AnswerModel deleteExhibition(@PathVariable Integer id) {
    return exhibitionFacade.deleteExhibition(id);
  }
}
