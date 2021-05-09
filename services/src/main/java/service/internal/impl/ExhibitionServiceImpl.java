package service.internal.impl;

import museum.domen.ExhibitionModel;
import museum.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.internal.ExhibitService;
import service.internal.ExhibitionService;
import service.internal.FileLoaderService;
import service.internal.LikeService;
import service.mapper.ExhibitionMapper;
import service.model.AnswerModel;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;
import src.model.MuseumStateEnum;
import src.model.TypeOfArtEnum;
import validation.ValidationErrorTerms;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {

  private final ExhibitionRepository exhibitionRepository;
  private final ExhibitionMapper exhibitionMapper;
  private final ExhibitService exhibitService;
  private final FileLoaderService fileLoaderService;
  private final LikeService likeService;

  @Autowired
  public ExhibitionServiceImpl(final ExhibitionMapper exhibitionMapper, ExhibitService exhibitService,
                               final FileLoaderService fileLoaderService, final ExhibitionRepository exhibitionRepository, LikeService likeService) {
    this.exhibitionMapper = exhibitionMapper;
    this.exhibitService = exhibitService;
    this.exhibitionRepository = exhibitionRepository;
    this.fileLoaderService = fileLoaderService;
    this.likeService = likeService;
  }

  private List<ExistingExhibition> toListExhibitions(List<ExhibitionModel> actualList) {
    List<ExistingExhibition> exhibitionList = new ArrayList<>();
    for (ExhibitionModel exhibitionModel : actualList) {
      exhibitionList.add(exhibitionMapper.toExistingExhibition(exhibitionModel));
    }
    return exhibitionList;
  }

  @Override
  public List<ExistingExhibition> getAllExhibitions() {
    List<ExhibitionModel> exhibitionModels = exhibitionRepository.findExhibitionModelsByMuseumState(MuseumStateEnum.ACTIVE);
    return toListExhibitions(exhibitionModels);
  }

  @Override
  public ExistingExhibition createExhibition(BaseExhibition exhibition) {
    if (exhibition.getMuseum().getId() != null) {
      ExhibitionModel exhibitionModel = exhibitionMapper.toExhibitionModel(exhibition);
      ExhibitionModel newExhbtnModel = exhibitionRepository.save(exhibitionModel);
      return exhibitionMapper.toExistingExhibition(newExhbtnModel);
    }
    throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
  }

  @Override
  public ExistingExhibition updateExhibition(MultipartFile file, ExistingExhibition exhibition) {
    String url = null;
    if (!file.getOriginalFilename().isEmpty()) {
      url = fileLoaderService.uploadImage(file);
    }

    ExhibitionModel exhibitionModel = exhibitionRepository.findById(exhibition.getId());
    if (exhibitionModel != null) {
      if (exhibition.getName() != null && !exhibition.getDescription().isEmpty()) {
        exhibitionModel.setDescription(exhibition.getDescription());
      }
      if (exhibition.getFirstDate() != null && !exhibition.getFirstDate().isEmpty()) {
        exhibitionModel.setFirstDate(exhibition.getFirstDate());
      }
      if (url != null && !url.isEmpty()) {
        fileLoaderService.deleteImage(exhibitionModel.getImageUrl());
        exhibitionModel.setImageUrl(url);
      }
      if (exhibition.getLastDate() != null && !exhibition.getLastDate().isEmpty()) {
        exhibitionModel.setLastDate(exhibition.getLastDate());
      }
      if (exhibition.getName() != null && !exhibition.getName().isEmpty()) {
        exhibitionModel.setName(exhibition.getName());
      }
      ExhibitionModel newExhbtnModel = exhibitionRepository.save(exhibitionModel);
      return exhibitionMapper.toExistingExhibition(newExhbtnModel);
    }
    return null;
  }

  @Override
  public List<ExistingExhibition> getExhibitionsByMuseumId(Integer id) {
    List<ExhibitionModel> actualList = exhibitionRepository.findExhibitionModelsByMuseumId(id);
    return toListExhibitions(actualList);
  }

  @Override
  public AnswerModel deleteExhibition(Integer id) {
    ExhibitionModel exhibitionModel = exhibitionRepository.findById(id);
    if (exhibitionModel != null) {
      fileLoaderService.deleteImage(exhibitionModel.getImageUrl());
      exhibitService.deleteExhibitsExhibitionId(id);
      likeService.deleteArts(id, TypeOfArtEnum.EXHIBITION);
      exhibitionRepository.delete(exhibitionModel);
      return new AnswerModel("Выставка удалена");
    }
    throw new IllegalArgumentException(ValidationErrorTerms.EXHIBITION_NOT_EXIST);
  }

  @Override
  public ExistingExhibition getExhibitionById(Integer id) {
    return exhibitionMapper.toExistingExhibition(exhibitionRepository.findById(id));
  }

  @Override
  public List<ExistingExhibition> getLikedExhibitionsByUser(Integer idUser) {
    List<ExhibitionModel> exhibitionModels = exhibitionRepository.getLikedExhibitionsByUser(idUser);
    List<ExistingExhibition> existingExhibits = new ArrayList<>();
    for (ExhibitionModel exhibitionModel : exhibitionModels) {
      existingExhibits.add(exhibitionMapper.toExistingExhibition(exhibitionModel));
    }
    return existingExhibits;
  }
}
