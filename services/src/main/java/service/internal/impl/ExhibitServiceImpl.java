package service.internal.impl;

import museum.domen.ExhibitModel;
import museum.mapper.AuthorMapper;
import museum.mapper.ExhibitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.internal.ExhibitService;
import service.mapper.ExhibitStruct;
import service.model.exhibit.BaseExhibit;
import service.model.exhibit.ExistingExhibit;

import java.util.ArrayList;
import java.util.List;

public class ExhibitServiceImpl implements ExhibitService {

  private final ExhibitMapper exhibitMapper;
  private final AuthorMapper authorMapper;

  private final ExhibitStruct exhibitStruct;

  @Autowired
  public ExhibitServiceImpl(final ExhibitMapper exhibitMapper, final AuthorMapper authorMapper, final ExhibitStruct exhibitStruct) {
    this.exhibitMapper = exhibitMapper;
    this.authorMapper = authorMapper;
    this.exhibitStruct = exhibitStruct;
  }

  @Override
  public List<ExistingExhibit> getAllExhibits() {
    Iterable<ExhibitModel> exhibitModels = exhibitMapper.findAll();
    List<ExhibitModel> actualList = new ArrayList<>();
    while (exhibitModels.iterator().hasNext()) {
      actualList.add(exhibitModels.iterator().next());
    }
    return exhibitStruct.toListExistingExhibits(actualList);

  }

  @Override
  public List<ExistingExhibit> getExhibitsByMuseumId(Integer id) {
    List<ExhibitModel> actualList = exhibitMapper.findExhibitModelsByExhibition_Museum_Id(id);
    return exhibitStruct.toListExistingExhibits(actualList);
  }

  @Override
  public ExistingExhibit createExhibit(BaseExhibit exhibit) {
    ExhibitModel exhibitionModel = exhibitStruct.toExhibitModel(exhibit);
    return exhibitStruct.toExistingExhibit(exhibitMapper.save(exhibitionModel));
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
      if (exhibit.getAuthorId() != null) {
        exhibitModel.setAuthor(authorMapper.findOne((long) exhibit.getAuthorId()));
      }
      if (!exhibit.getName().isEmpty()) {
        exhibitModel.setName(exhibit.getName());
      }
      return exhibitStruct.toExistingExhibit(exhibitMapper.save(exhibitModel));
    }
    return null;
  }
}
