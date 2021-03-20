package service.internal.impl;

import museum.domen.ExhibitModel;
import museum.mapper.AuthorMapper;
import museum.mapper.ExhibitMapper;
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
  private final MuseumStruct museumStruct;

  @Autowired
  public ExhibitServiceImpl(final ExhibitMapper exhibitMapper, final AuthorMapper authorMapper,
                            final AuthorStruct authorStruct, ExhibitionStruct exhibitionStruct,
                            final MuseumStruct museumStruct,
                            final ExhibitStruct exhibitStruct) {
    this.exhibitMapper = exhibitMapper;
    this.authorMapper = authorMapper;
    this.authorStruct = authorStruct;
    this.museumStruct = museumStruct;
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
      ExistingMuseum museum = museumStruct.toExistingMuseum(exhibitModel.exhibition.museum);
      ExistingExhibition existingExhibition = exhibitionStruct.toExistingExhibition(exhibitModel.exhibition, museum);
      existingExhibits.add(exhibitStruct.toExistingExhibit(exhibitModel, existingAuthor, existingExhibition));
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
    ExhibitModel exhibitModel = exhibitStruct.toExhibitModel(exhibit);
    ExistingAuthor existingAuthor = authorStruct.toExistingAuthor(exhibitModel.author);
    ExistingMuseum museum = museumStruct.toExistingMuseum(exhibitModel.exhibition.museum);
    ExistingExhibition existingExhibition = exhibitionStruct.toExistingExhibition(exhibitModel.exhibition, museum);
    return exhibitStruct.toExistingExhibit(exhibitModel, existingAuthor, existingExhibition);
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
        exhibitModel.setAuthor(authorMapper.findOne((long) exhibit.getAuthor().getId()));
      }
      if (!exhibit.getName().isEmpty()) {
        exhibitModel.setName(exhibit.getName());
      }
      ExhibitModel newExhibitModel = exhibitMapper.save(exhibitModel);
      ExistingAuthor existingAuthor = authorStruct.toExistingAuthor(newExhibitModel.author);
      ExistingMuseum museum = museumStruct.toExistingMuseum(exhibitModel.exhibition.museum);
      ExistingExhibition existingExhibition = exhibitionStruct.toExistingExhibition(exhibitModel.exhibition, museum);
      return exhibitStruct.toExistingExhibit(newExhibitModel, existingAuthor, existingExhibition);
    }
    return null;
  }
}
