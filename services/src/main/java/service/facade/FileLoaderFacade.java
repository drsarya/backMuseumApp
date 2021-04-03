package service.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
@Validated
public interface FileLoaderFacade {
  ResponseEntity<String> uploadImage(MultipartFile aFile);

}
