package service.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.facade.FileLoaderFacade;
import service.internal.FileLoaderService;
import service.internal.MuseumService;

@Service
public class FileLoaderFacadeImpl implements FileLoaderFacade {
  private final FileLoaderService fileLoaderService;

  public FileLoaderFacadeImpl(final FileLoaderService fileLoaderService) {
    this.fileLoaderService = fileLoaderService;
  }

  @Override
  public  String  uploadImage(MultipartFile aFile) {
    return fileLoaderService.uploadImage(aFile);
  }
}
