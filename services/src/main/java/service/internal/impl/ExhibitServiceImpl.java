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
import service.mapper.ExhibitionStruct;
import service.mapper.MuseumStruct;
import service.model.OkModel;
import service.model.author.ExistingAuthor;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.museum.ExistingMuseum;
import src.model.MuseumStateEnum;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitServiceImpl implements ExhibitService {

  private final ExhibitMapper exhibitMapper;
  private final AuthorMapper authorMapper;
  private final ExhibitStruct exhibitStruct;
  private final AuthorStruct authorStruct;
  private final ExhibitionStruct exhibitionStruct;
  private final ExhibitionMapper exhibitionMapper;

  private final FileLoaderService fileLoaderService;

  @Autowired
  public ExhibitServiceImpl(final ExhibitMapper exhibitMapper, final AuthorMapper authorMapper,
                            final AuthorStruct authorStruct, ExhibitionStruct exhibitionStruct,
                            final FileLoaderService fileLoaderService, final ExhibitionMapper exhibitionMapper,
                            final ExhibitStruct exhibitStruct) {
    this.exhibitMapper = exhibitMapper;
    this.authorMapper = authorMapper;
    this.authorStruct = authorStruct;
    this.fileLoaderService = fileLoaderService;
    this.exhibitionMapper = exhibitionMapper;
    this.exhibitionStruct = exhibitionStruct;
    this.exhibitStruct = exhibitStruct;
  }

  @Override
  public List<ExistingExhibit> getAllExhibits() {
    Iterable<ExhibitModel> exhibitModels = exhibitMapper.findExhibitModelsByExhibition_Museum_State(MuseumStateEnum.ACTIVE);
    List<ExhibitModel> actualList = new ArrayList<>();
    while (exhibitModels.iterator().hasNext()) {
      actualList.add(exhibitModels.iterator().next());
    }
    return toListExhibits(actualList);
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
  public OkModel deleteExhibit(Integer id) {
    ExhibitModel exhibitionModel = exhibitMapper.findById(id);
    if (exhibitionModel != null) {
      exhibitMapper.delete(exhibitionModel);
      return new OkModel("Экспонат удален");
    }
    throw new IllegalArgumentException("Ошибка удаления");
  }

  @Override
  public List<ExistingExhibit> getExhibitsByExhibitionId(Integer id) {
    List<ExhibitModel> actualList = exhibitMapper.findExhibitModelsByExhibition_Id(id);
    return toListExhibits(actualList);
  }


  @Override
  public ExistingExhibit updateExhibit(MultipartFile upload, ExistingExhibit exhibit) {
    ExhibitModel exhibitModel = exhibitMapper.findById(  exhibit.getId());

    String url = null;
    if (!upload.getOriginalFilename().isEmpty()) {
      url = fileLoaderService.uploadImage(upload);
    }
    if (exhibitModel != null) {
      if (!exhibit.getDescription().isEmpty()) {
        exhibitModel.setDescription(exhibit.getDescription());
      }
      if (!exhibit.getDateOfCreate().isEmpty()) {
        exhibitModel.setDateOfCreate(exhibit.getDateOfCreate());
      }
      if (url !=null && !url.isEmpty()) {
        exhibitModel.setImageUrl(exhibit.getImageUrl());
      }
      if (exhibit.getAuthor() != null) {
        exhibitModel.setAuthor(authorMapper.findByFullName(exhibit.getAuthor().getFullName()));
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
