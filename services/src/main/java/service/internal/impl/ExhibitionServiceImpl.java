package service.internal.impl;

import museum.domen.AuthorModel;
import museum.domen.ExhibitionModel;
import museum.mapper.AuthorMapper;
import museum.mapper.ExhibitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.internal.ExhibitionService;
import service.mapper.AuthorStruct;
import service.mapper.ExhibitionStruct;
import service.model.exhibition.ExistingExhibition;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionServiceImpl implements ExhibitionService {


  private final ExhibitionMapper exhibitionMapper;
  private final ExhibitionStruct exhibitionStruct;

  @Autowired
  public ExhibitionServiceImpl(final ExhibitionStruct exhibitionStruct, final ExhibitionMapper exhibitionMapper) {
    this.exhibitionStruct = exhibitionStruct;
    this.exhibitionMapper = exhibitionMapper;
  }

  @Override
  public List<ExistingExhibition> getAllExhibitions() {
    Iterable<ExhibitionModel> exhibitionModels = exhibitionMapper.findAll();
    List<ExhibitionModel> actualList = new ArrayList<>();
    while (exhibitionModels.iterator().hasNext()) {
      actualList.add(exhibitionModels.iterator().next());
    }
    return exhibitionStruct.toListExistingExhibitions(actualList);

  }

  @Override
  public ExistingExhibition createExhibition(ExistingExhibition exhibition) {
    ExhibitionModel exhibitionModel = exhibitionStruct.toExhibitionModel(exhibition);
    return exhibitionStruct.toExistingExhibition(exhibitionMapper.save(exhibitionModel));

  }

  @Override
  public ExistingExhibition updateExhibition(ExistingExhibition exhibition) {
    ExhibitionModel exhibitionModel = exhibitionMapper.findOne((long) exhibition.getId());
    if (exhibitionModel != null) {
      if (!exhibition.getDescription().isEmpty()) {
        exhibitionModel.setDescription(exhibition.getDescription());
      }
      if (!exhibition.getFirstDate().isEmpty()) {
        exhibitionModel.setFirstDate(exhibition.getFirstDate());
      }
      if (!exhibition.getImageUrl().isEmpty()) {
        exhibitionModel.setImageUrl(exhibition.getImageUrl());
      }
      if (!exhibition.getLastDate().isEmpty()) {
        exhibitionModel.setLastDate(exhibition.getLastDate());
      }
      if (!exhibition.getName().isEmpty()) {
        exhibitionModel.setName(exhibition.getName());
      }
      return exhibitionStruct.toExistingExhibition(exhibitionMapper.save(exhibitionModel));
    }
    return null;
  }

  @Override
  public List<ExistingExhibition> getExhibitionsByMuseumId(int id) {
    List<ExhibitionModel> actualList = exhibitionMapper.findExhibitionModelsByMuseumId(id);
    return exhibitionStruct.toListExistingExhibitions(actualList);
  }

  @Override
  public boolean deleteExhibition(int id) {
    if (exhibitionMapper.exists((long) id)) {
      exhibitionMapper.delete((long) id);
      return true;
    }
    return false;
  }
}
