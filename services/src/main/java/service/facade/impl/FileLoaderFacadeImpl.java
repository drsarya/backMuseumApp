package service.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.facade.FileLoaderFacade;
import service.internal.FileLoaderService;

@Service
public class FileLoaderFacadeImpl implements FileLoaderFacade {
  private final FileLoaderService fileLoaderService;

  public FileLoaderFacadeImpl(final FileLoaderService fileLoaderService) {
    this.fileLoaderService = fileLoaderService;
  }

  @Override
  public String uploadImage(MultipartFile aFile) {
    return fileLoaderService.uploadImage(aFile);
  }
}
