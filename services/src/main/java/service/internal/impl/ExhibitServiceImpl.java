package service.internal.impl;

import museum.domen.AuthorModel;
import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.mapper.AuthorMapper;
import museum.mapper.ExhibitMapper;
import museum.mapper.ExhibitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.ExhibitService;
import service.mapper.AuthorStruct;
import service.mapper.ExhibitStruct;
import service.mapper.ExhibitionStruct;
import service.mapper.MuseumStruct;
import service.model.author.ExistingAuthor;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.museum.ExistingMuseum;

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


  @Autowired
  public ExhibitServiceImpl(final ExhibitMapper exhibitMapper, final AuthorMapper authorMapper,
                            final AuthorStruct authorStruct, ExhibitionStruct exhibitionStruct,
                            final ExhibitionMapper exhibitionMapper,
                            final ExhibitStruct exhibitStruct) {
    this.exhibitMapper = exhibitMapper;
    this.authorMapper = authorMapper;
    this.authorStruct = authorStruct;
    this.exhibitionMapper = exhibitionMapper;
    this.exhibitionStruct = exhibitionStruct;
    this.exhibitStruct = exhibitStruct;
  }

  @Override
  public List<ExistingExhibit> getAllExhibits() {
    Iterable<ExhibitModel> exhibitModels = exhibitMapper.findAll();
    List<ExhibitModel> actualList = new ArrayList<>();
    while (exhibitModels.iterator().hasNext()) {
      actualList.add(exhibitModels.iterator().next());
    }
    return toListExhibits(actualList);
  }

  private List<ExistingExhibit> toListExhibits(List<ExhibitModel> actualList) {
    List<ExistingExhibit> existingExhibits = new ArrayList<>();
    for (ExhibitModel exhibitModel : actualList) {
      ExistingAuthor existingAuthor = authorStruct.toExistingAuthor(exhibitModel.author);
      existingExhibits.add(exhibitStruct.toExistingExhibit(exhibitModel, existingAuthor, exhibitModel.exhibition.getId()));
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
    if(authorModel==null){
      authorModel = new AuthorModel();
      authorModel.setFullName(exhibit.getAuthor().getFullName());
      authorModel = authorMapper.save(authorModel);
    }

    ExhibitionModel exhibitionModel = exhibitionMapper.findOne((long) exhibit.getExhibitionId());
    ExhibitModel exhibitModel = exhibitStruct.toExhibitModel(exhibit, exhibitionModel);
    exhibitModel.setAuthor(authorModel);
    ExhibitModel exhibitModel1 = exhibitMapper.save(exhibitModel);
    ExistingAuthor existingAuthor = authorStruct.toExistingAuthor(exhibitModel1.author);
    return exhibitStruct.toExistingExhibit(exhibitModel, existingAuthor, exhibitModel1.getExhibition().id);
  }

  @Override
  public boolean deleteExhibit(int id) {
    if (exhibitMapper.exists((long) id)) {
      exhibitMapper.delete((long) id);
      return true;
    }
    return false;
  }


  @Override
  public ExistingExhibit updateExhibit(ExistingExhibit exhibit) {
    ExhibitModel exhibitModel = exhibitMapper.findOne((long) exhibit.getId());
    if (exhibitModel != null) {
      if (!exhibit.getDescription().isEmpty()) {
        exhibitModel.setDescription(exhibit.getDescription());
      }
      if (!exhibit.getDateOfCreate().isEmpty()) {
        exhibitModel.setDateOfCreate(exhibit.getDateOfCreate());
      }
      if (!exhibit.getImageUrl().isEmpty()) {
        exhibitModel.setImageUrl(exhibit.getImageUrl());
      }
      if (exhibit.getAuthor() != null) {
        exhibitModel.setAuthor(authorMapper.findByFullName(  exhibit.getAuthor().getFullName()));
      }
      if (!exhibit.getName().isEmpty()) {
        exhibitModel.setName(exhibit.getName());
      }
      ExhibitModel newExhibitModel = exhibitMapper.save(exhibitModel);
      ExistingAuthor existingAuthor = authorStruct.toExistingAuthor(newExhibitModel.author);
      return exhibitStruct.toExistingExhibit(newExhibitModel, existingAuthor, newExhibitModel.exhibition.getId());
    }
    return null;
  }
}
