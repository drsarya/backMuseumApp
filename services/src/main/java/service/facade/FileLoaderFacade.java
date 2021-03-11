package service.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileLoaderFacade {
  ResponseEntity<String> uploadImage(MultipartFile aFile);

}
