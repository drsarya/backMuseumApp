package museum.api;

import org.cloudinary.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.facade.FileLoaderFacade;
import service.facade.MuseumFacade;
import service.model.museum.ExistingMuseum;
import service.model.museum.UpdatableMuseum;

@RestController
@RequestMapping(
  value = "/image",
  produces = "application/json"
)
public class FileApi {

  private final FileLoaderFacade fileLoaderFacade;
  private static final Logger logger = LoggerFactory.getLogger(MuseumApi.class);

  @Autowired
  public FileApi(final FileLoaderFacade fileLoaderFacade) {
    this.fileLoaderFacade = fileLoaderFacade;
  }


  @PostMapping(consumes = {"multipart/form-data"}, value = "/upload")
  @ResponseStatus(HttpStatus.CREATED)
  String updateMuseum(@RequestPart("imageUpload") MultipartFile upload) {

    ResponseEntity<String> s = fileLoaderFacade.uploadImage(upload);
    JSONObject json = new JSONObject(s.getBody());
    String url = json.getString("url");
    return url;
  }
}
