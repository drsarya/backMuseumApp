package service.facade;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Validated
public interface FileLoaderFacade {
  String uploadImage(MultipartFile aFile);
}
