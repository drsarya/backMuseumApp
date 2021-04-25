package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.facade.FileLoaderFacade;
import service.model.OkModel;
import service.model.exhibit.ExistingExhibit;

import java.util.List;

@RestController
@RequestMapping(
  value = "/image",
  produces = "application/json"
)
public class FileApi {

  private final FileLoaderFacade fileLoaderFacade;

  @Autowired
  public FileApi(final FileLoaderFacade fileLoaderFacade) {
    this.fileLoaderFacade = fileLoaderFacade;
  }

  @PostMapping(consumes = {"multipart/form-data"}, value = "/upload")
  @ResponseStatus(HttpStatus.CREATED)
  OkModel uploadImage(@RequestPart("imageUpload") MultipartFile upload) {
    String s = fileLoaderFacade.uploadImage(upload);
    return new OkModel(s);
  }
  @GetMapping(value = "")
  OkModel getExhibitsByExhibitionId() {
    return new OkModel("okkkkkkkkkkk");
  }
}
