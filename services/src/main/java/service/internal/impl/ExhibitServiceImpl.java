package service.internal.impl;

import museum.domen.ExhibitModel;
import museum.repository.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.internal.AuthorService;
import service.internal.ExhibitService;
import service.internal.FileLoaderService;
import service.internal.LikeService;
import service.mapper.ExhibitMapper;
import service.model.AnswerModel;
import service.model.author.ExistingAuthor;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;
import src.model.MuseumStateEnum;
import src.model.TypeOfArtEnum;
import validation.ValidationErrorTerms;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitServiceImpl implements ExhibitService {

  private final ExhibitRepository exhibitRepository;
  private final ExhibitMapper exhibitMapper;
  private final AuthorService authorService;
  private final FileLoaderService fileLoaderService;
  private final LikeService likeService;

  @Autowired
  public ExhibitServiceImpl(final ExhibitRepository exhibitRepository, final FileLoaderService fileLoaderService,
                            final ExhibitMapper exhibitMapper, final AuthorService authorService, LikeService likeService) {
    this.exhibitRepository = exhibitRepository;
    this.fileLoaderService = fileLoaderService;
    this.exhibitMapper = exhibitMapper;
    this.authorService = authorService;
    this.likeService = likeService;
  }

  @Override
  public List<ExistingExhibit> getAllExhibits() {
    List<ExhibitModel> exhibitModels = exhibitRepository.findExhibitModelsByExhibition_Museum_State(MuseumStateEnum.ACTIVE);
    return toListExhibits(exhibitModels);
  }

  private List<ExistingExhibit> toListExhibits(List<ExhibitModel> actualList) {
    List<ExistingExhibit> existingExhibits = new ArrayList<>();
    for (ExhibitModel exhibitModel : actualList) {
      existingExhibits.add(exhibitMapper.toExistingExhibit(exhibitModel));
    }
    return existingExhibits;
  }

  @Override
  public List<ExistingExhibit> getExhibitsByMuseumId(Integer id) {
    List<ExhibitModel> actualList = exhibitRepository.getMuseumExhibits(id);
    return toListExhibits(actualList);
  }

  @Override
  public ExistingExhibit createExhibit(BaseExhibit exhibit) {
    ExhibitModel exhibitModel = exhibitMapper.toExhibitModel(exhibit);
    exhibitModel.getAuthor().setId(authorService.findAuthorByFullName(exhibit.getAuthor().getFullName()).getId());
    return exhibitMapper.toExistingExhibit(exhibitRepository.save(exhibitModel));

  }

  @Override
  public List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser) {
    List<ExhibitModel> exhibitionModels = exhibitRepository.getLikedExhibitsByUser(idUser);
    List<ExistingExhibit> existingExhibits = new ArrayList<>();
    for (ExhibitModel exhibitModel : exhibitionModels) {
      existingExhibits.add(exhibitMapper.toExistingExhibit(exhibitModel));
    }
    return existingExhibits;
  }

  @Override
  public AnswerModel deleteExhibit(Integer id) {
    ExhibitModel exhibitionModel = exhibitRepository.findById(id);
    if (exhibitionModel != null) {
      likeService.deleteArts(id, TypeOfArtEnum.EXHIBIT);
      fileLoaderService.deleteImage(exhibitionModel.getImageUrl());
      exhibitRepository.delete(exhibitionModel);
      return new AnswerModel("Экспонат удален");
    }
    throw new IllegalArgumentException(ValidationErrorTerms.ERROR_OF_DELETE);
  }

  @Override
  public List<ExistingExhibit> getExhibitsByExhibitionId(Integer id) {
    List<ExhibitModel> actualList = exhibitRepository.findExhibitModelsByExhibition_Id(id);
    return toListExhibits(actualList);
  }

  @Override
  public void deleteExhibitsExhibitionId(Integer id) {
    List<ExhibitModel> actualList = exhibitRepository.findExhibitModelsByExhibition_Id(id);
    for (ExhibitModel ex : actualList) {
      likeService.deleteArts(ex.getId(), TypeOfArtEnum.EXHIBIT);
      fileLoaderService.deleteImage(ex.getImageUrl());
    }
  }

  @Override
  public ExistingExhibit updateExhibit(MultipartFile upload, ExistingExhibit exhibit) {
    ExhibitModel exhibitModel = exhibitRepository.findById(exhibit.getId());
    String url = null;
    if (upload.getOriginalFilename() != null && !upload.getOriginalFilename().isEmpty()) {
      url = fileLoaderService.uploadImage(upload);
    }
    if (exhibitModel != null) {
      if (!exhibit.getDescription().isEmpty()) {
        exhibitModel.setDescription(exhibit.getDescription());
      }
      if (!exhibit.getDateOfCreate().isEmpty()) {
        exhibitModel.setDateOfCreate(exhibit.getDateOfCreate());
      }
      if (url != null && !url.isEmpty()) {
        fileLoaderService.deleteImage(exhibitModel.getImageUrl());
        exhibitModel.setImageUrl(url);
      }
      if (exhibit.getAuthor() != null) {
        ExistingAuthor authorModel = authorService.findAuthorByFullName(exhibit.getAuthor().getFullName());
        exhibitModel.getAuthor().setId(authorModel.getId());
      }
      if (!exhibit.getName().isEmpty()) {
        exhibitModel.setName(exhibit.getName());
      }
      ExhibitModel newExhibitModel = exhibitRepository.save(exhibitModel);
      return exhibitMapper.toExistingExhibit(newExhibitModel);
    }
    return null;
  }
}
