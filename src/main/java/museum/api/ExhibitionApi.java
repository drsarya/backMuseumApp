package museum.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.facade.ExhibitionFacade;
import service.facade.LikeFacade;
import service.model.OkModel;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping(
  value = "/exhibitions",
  produces = "application/json"
)
public class ExhibitionApi {

  private final ExhibitionFacade exhibitionFacade;
  private final FileApi fileApi;

  @Autowired
  public ExhibitionApi(final ExhibitionFacade exhibitionFacade, final FileApi fileApi) {
    this.exhibitionFacade = exhibitionFacade;
    this.fileApi = fileApi;
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


  @GetMapping(value = "/{id}")
  List<ExistingExhibition> getExhibitionsByMuseumId(@PathVariable Integer id) {
    return exhibitionFacade.getExhibitionsByMuseumId(id);
  }

  @DeleteMapping(value = "/{id}")
  OkModel deleteExhibition(@PathVariable Integer id) {
    return exhibitionFacade.deleteExhibition(id);
  }
}
