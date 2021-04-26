package service.internal.impl;

import museum.domen.ExhibitionModel;
import museum.domen.MuseumModel;
import museum.mapper.ExhibitionMapper;
import museum.mapper.MuseumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.internal.ExhibitionService;
import service.internal.FileLoaderService;
import service.mapper.ExhibitionStruct;
import service.mapper.MuseumStruct;
import service.model.AnswerModel;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;
import service.model.museum.ShortInfoMuseum;
import src.model.MuseumStateEnum;
import validation.ValidationErrorTerms;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {


  private final ExhibitionMapper exhibitionMapper;
  private final ExhibitionStruct exhibitionStruct;
  private final MuseumStruct museumStruct;
  private final FileLoaderService fileLoaderService;
  private final MuseumMapper museumMapper;

  @Autowired
  public ExhibitionServiceImpl(final ExhibitionStruct exhibitionStruct, final FileLoaderService fileLoaderService, final MuseumMapper museumMapper, final ExhibitionMapper exhibitionMapper, final MuseumStruct museumStruct) {
    this.exhibitionStruct = exhibitionStruct;
    this.exhibitionMapper = exhibitionMapper;
    this.museumStruct = museumStruct;
    this.fileLoaderService = fileLoaderService;
    this.museumMapper = museumMapper;
  }

  private List<ExistingExhibition> toListExhibitions(List<ExhibitionModel> actualList) {
    List<ExistingExhibition> exhibitionList = new ArrayList<>();
    for (ExhibitionModel exhibitionModel : actualList) {
      ShortInfoMuseum museum = museumStruct.toShortInfoMuseum(exhibitionModel.getMuseum());
      exhibitionList.add(exhibitionStruct.toExistingExhibition(exhibitionModel, museum));
    }
    return exhibitionList;
  }

  @Override
  public List<ExistingExhibition> getAllExhibitions() {
    List<ExhibitionModel> exhibitionModels = exhibitionMapper.findExhibitionModelsByMuseumState(MuseumStateEnum.ACTIVE);

    return toListExhibitions(exhibitionModels);
  }

  @Override
  public ExistingExhibition createExhibition(BaseExhibition exhibition) {
    MuseumModel museumModel = museumMapper.findById(exhibition.getMuseum().getId());
    if (museumModel != null) {
      ExhibitionModel exhibitionModel = exhibitionStruct.toExhibitionModel(exhibition, museumModel);
      ExhibitionModel newExhbtnModel = exhibitionMapper.save(exhibitionModel);
      ShortInfoMuseum museum = museumStruct.toShortInfoMuseum(exhibitionModel.getMuseum());
      return exhibitionStruct.toExistingExhibition(newExhbtnModel, museum);

    }
    throw new IllegalArgumentException(ValidationErrorTerms.MUSEUM_NOT_EXIST);
  }

  @Override
  public ExistingExhibition updateExhibition(MultipartFile file, ExistingExhibition exhibition) {
    String url = null;
    if (!file.getOriginalFilename().isEmpty()) {
      url = fileLoaderService.uploadImage(file);
    }

    ExhibitionModel exhibitionModel = exhibitionMapper.findById(exhibition.getId());
    if (exhibitionModel != null) {
      if (exhibition.getName() != null && !exhibition.getDescription().isEmpty()) {
        exhibitionModel.setDescription(exhibition.getDescription());
      }
      if (exhibition.getFirstDate() != null && !exhibition.getFirstDate().isEmpty()) {
        exhibitionModel.setFirstDate(exhibition.getFirstDate());
      }
      if (url != null && !url.isEmpty()) {
        exhibitionModel.setImageUrl(url);
      }
      if (exhibition.getLastDate() != null && !exhibition.getLastDate().isEmpty()) {
        exhibitionModel.setLastDate(exhibition.getLastDate());
      }
      if (exhibition.getName() != null && !exhibition.getName().isEmpty()) {
        exhibitionModel.setName(exhibition.getName());
      }
      ExhibitionModel newExhbtnModel = exhibitionMapper.save(exhibitionModel);
      ShortInfoMuseum museum = museumStruct.toShortInfoMuseum(exhibitionModel.getMuseum());

      return exhibitionStruct.toExistingExhibition(newExhbtnModel, museum);
    }
    return null;
  }

  @Override
  public List<ExistingExhibition> getExhibitionsByMuseumId(Integer id) {
    List<ExhibitionModel> actualList = exhibitionMapper.findExhibitionModelsByMuseumId(id);
    return toListExhibitions(actualList);
  }

  @Override
  public AnswerModel deleteExhibition(Integer id) {
    ExhibitionModel exhibitionModel = exhibitionMapper.findById(id);
    if (exhibitionModel != null) {
      exhibitionMapper.delete(exhibitionModel);
      return new AnswerModel("Выставка удалена");
    }
    throw new IllegalArgumentException(ValidationErrorTerms.EXHIBITION_NOT_EXIST);
  }

  @Override
  public List<ExistingExhibition> getLikedExhibitionsByUser(Integer idUser) {
    List<ExhibitionModel> exhibitionModels = exhibitionMapper.getLikedExhibitionsByUser(idUser);
    List<ExistingExhibition> existingExhibits = new ArrayList<>();
    for (ExhibitionModel exhibitionModel : exhibitionModels) {
      existingExhibits.add(exhibitionStruct.toExistingExhibition(exhibitionModel, museumStruct.toShortInfoMuseum(exhibitionModel.getMuseum())));
    }
    return existingExhibits;
  }
}
