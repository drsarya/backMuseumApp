package service.internal.impl;

import museum.domen.AuthorModel;
import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.mapper.AuthorMapper;
import museum.mapper.ExhibitMapper;
import museum.mapper.ExhibitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.internal.ExhibitService;
import service.internal.FileLoaderService;
import service.mapper.AuthorStruct;
import service.mapper.ExhibitStruct;
import service.model.AnswerModel;
import service.model.author.ExistingAuthor;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;
import src.model.MuseumStateEnum;
import validation.ValidationErrorTerms;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitServiceImpl implements ExhibitService {

  private final ExhibitMapper exhibitMapper;
  private final AuthorMapper authorMapper;
  private final ExhibitStruct exhibitStruct;
  private final AuthorStruct authorStruct;
  private final ExhibitionMapper exhibitionMapper;
  private final FileLoaderService fileLoaderService;

  @Autowired
  public ExhibitServiceImpl(final ExhibitMapper exhibitMapper, final AuthorMapper authorMapper,
                            final AuthorStruct authorStruct,
                            final FileLoaderService fileLoaderService, final ExhibitionMapper exhibitionMapper,
                            final ExhibitStruct exhibitStruct) {
    this.exhibitMapper = exhibitMapper;
    this.authorMapper = authorMapper;
    this.authorStruct = authorStruct;
    this.fileLoaderService = fileLoaderService;
    this.exhibitionMapper = exhibitionMapper;
    this.exhibitStruct = exhibitStruct;
  }

  @Override
  public List<ExistingExhibit> getAllExhibits() {
    List<ExhibitModel> exhibitModels = exhibitMapper.findExhibitModelsByExhibition_Museum_State(MuseumStateEnum.ACTIVE);

    return toListExhibits(exhibitModels);
  }

  private List<ExistingExhibit> toListExhibits(List<ExhibitModel> actualList) {
    List<ExistingExhibit> existingExhibits = new ArrayList<>();
    for (ExhibitModel exhibitModel : actualList) {
      ExistingAuthor existingAuthor = authorStruct.toExistingAuthor(exhibitModel.getAuthor());
      existingExhibits.add(exhibitStruct.toExistingExhibit(exhibitModel, existingAuthor, exhibitModel.getExhibition().getId()));
    }
    return existingExhibits;
  }

  @Override
  public List<ExistingExhibit> getExhibitsByMuseumId(Integer id) {
    List<ExhibitModel> actualList = exhibitMapper.findExhibitModelsByExhibition_Museum_Id(id);

    return toListExhibits(actualList);
  }

  @Override
  public ExistingExhibit createExhibit(BaseExhibit exhibit) {
    AuthorModel authorModel = authorMapper.findByFullName(exhibit.getAuthor().getFullName());
    if (authorModel == null) {
      authorModel = new AuthorModel();
      authorModel.setFullName(exhibit.getAuthor().getFullName());
      authorModel = authorMapper.save(authorModel);
    }

    ExhibitionModel exhibitionModel = exhibitionMapper.findById(exhibit.getExhibitionId());
    ExhibitModel exhibitModel = exhibitStruct.toExhibitModel(exhibit, exhibitionModel);
    exhibitModel.setAuthor(authorModel);
    ExhibitModel exhibitModel1 = exhibitMapper.save(exhibitModel);
    ExistingAuthor existingAuthor = authorStruct.toExistingAuthor(exhibitModel1.getAuthor());
    return exhibitStruct.toExistingExhibit(exhibitModel, existingAuthor, exhibitModel1.getExhibition().getId());
  }

  @Override
  public List<ExistingExhibit> getLikedExhibitsByUser(Integer idUser) {
    List<ExhibitModel> exhibitionModels = exhibitMapper.getLikedExhibitsByUser(idUser);
    List<ExistingExhibit> existingExhibits = new ArrayList<>();
    for (ExhibitModel exhibitModel : exhibitionModels) {
      existingExhibits.add(exhibitStruct.toExistingExhibit(exhibitModel, authorStruct.toExistingAuthor(exhibitModel.getAuthor()), exhibitModel.getExhibition().getId()));
    }
    return existingExhibits;
  }

  @Override
  public AnswerModel deleteExhibit(Integer id) {
    ExhibitModel exhibitionModel = exhibitMapper.findById(id);
    if (exhibitionModel != null) {
      exhibitMapper.delete(exhibitionModel);
      return new AnswerModel("Экспонат удален");
    }
    throw new IllegalArgumentException(ValidationErrorTerms.ERROR_OF_DELETE);
  }

  @Override
  public List<ExistingExhibit> getExhibitsByExhibitionId(Integer id) {
    List<ExhibitModel> actualList = exhibitMapper.findExhibitModelsByExhibition_Id(id);
    return toListExhibits(actualList);
  }


  @Override
  public ExistingExhibit updateExhibit(MultipartFile upload, ExistingExhibit exhibit) {
    ExhibitModel exhibitModel = exhibitMapper.findById(exhibit.getId());

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
        exhibitModel.setImageUrl(url);
      }
      if (exhibit.getAuthor() != null) {
        AuthorModel authorModel = authorMapper.findByFullName(exhibit.getAuthor().getFullName());
        if (authorModel == null) {
          authorModel = new AuthorModel();
          authorModel.setFullName(exhibit.getAuthor().getFullName());
          authorMapper.save(authorModel);
        }
        exhibitModel.setAuthor(authorModel);
      }
      if (!exhibit.getName().isEmpty()) {
        exhibitModel.setName(exhibit.getName());
      }
      ExhibitModel newExhibitModel = exhibitMapper.save(exhibitModel);
      ExistingAuthor existingAuthor = authorStruct.toExistingAuthor(newExhibitModel.getAuthor());
      return exhibitStruct.toExistingExhibit(newExhibitModel, existingAuthor, newExhibitModel.getExhibition().getId());
    }
    return null;
  }
}
