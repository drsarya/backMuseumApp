package museum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.facade.FileLoaderFacade;
import service.model.AnswerModel;

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
  AnswerModel uploadImage(@RequestPart("imageUpload") MultipartFile upload) {
    String s = fileLoaderFacade.uploadImage(upload);
    return new AnswerModel(s);
  }
  @GetMapping( )
  AnswerModel getExhibitsByExhibitionId() {
    return new AnswerModel("Он дружелюбный мальчик , но раньше все ломал\n" +
      "Минутка просвещения и он все осознал\n" +
      "Не нужно только портить , ведь можно создавать\n" +
      "Сегодня приложенья он учится писать!!! \n Идёт теперь под ручку не с тестами , а с ним\n" +
      "С тем маленьким , зеленым , кружочком заводным\n" +
      "Так пусть же мысли льются, мечты не предавай\n" +
      "И знанья с аппетитом большим ты поглощай!!!!!");
  }
}
