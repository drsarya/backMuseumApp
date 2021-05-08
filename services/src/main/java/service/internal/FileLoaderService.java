package service.internal;

import org.springframework.web.multipart.MultipartFile;

public interface FileLoaderService {
  String uploadImage(MultipartFile aFile);
    void deleteImage(String url);
}
