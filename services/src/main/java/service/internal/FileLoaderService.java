package service.internal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileLoaderService {
  String uploadImage(MultipartFile aFile);
}
