package service.internal.impl;

import museum.domen.AuthorModel;
import museum.domen.ExhibitModel;
import museum.domen.ExhibitionModel;
import museum.mapper.AuthorMapper;
import museum.mapper.ExhibitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.ExhibitionService;
import service.mapper.AuthorStruct;
import service.mapper.ExhibitionStruct;
import service.mapper.MuseumStruct;
import service.model.author.ExistingAuthor;
import service.model.exhibit.ExistingExhibit;
import service.model.exhibition.ExistingExhibition;
import service.model.museum.ExistingMuseum;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {


  private final ExhibitionMapper exhibitionMapper;
  private final ExhibitionStruct exhibitionStruct;
  private final MuseumStruct museumStruct;

  @Autowired
  public ExhibitionServiceImpl(final ExhibitionStruct exhibitionStruct, final ExhibitionMapper exhibitionMapper, final MuseumStruct museumStruct) {
    this.exhibitionStruct = exhibitionStruct;
    this.exhibitionMapper = exhibitionMapper;
    this.museumStruct = museumStruct;

  }

  private List<ExistingExhibition> toListExhibitions(List<ExhibitionModel> actualList) {
    List<ExistingExhibition> exhibitionList = new ArrayList<>();
    for (ExhibitionModel exhibitionModel : actualList) {
      ExistingMuseum museum = museumStruct.toExistingMuseum(exhibitionModel.museum);
      exhibitionList.add(exhibitionStruct.toExistingExhibition(exhibitionModel, museum));
    }
    return exhibitionList;
  }

  @Override
  public List<ExistingExhibition> getAllExhibitions() {
    Iterable<ExhibitionModel> exhibitionModels = exhibitionMapper.findAll();

    List<ExhibitionModel> actualList = new ArrayList<>();
    while (exhibitionModels.iterator().hasNext()) {
      actualList.add(exhibitionModels.iterator().next());
    }

    return toListExhibitions(actualList);

  }

  @Override
  public ExistingExhibition createExhibition(ExistingExhibition exhibition) {
    ExhibitionModel exhibitionModel = exhibitionStruct.toExhibitionModel(exhibition);

    ExhibitionModel newExhbtnModel = exhibitionMapper.save(exhibitionModel);
    ExistingMuseum museum = museumStruct.toExistingMuseum(newExhbtnModel.museum);
    return exhibitionStruct.toExistingExhibition(newExhbtnModel, museum);

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
      ExhibitionModel newExhbtnModel = exhibitionMapper.save(exhibitionModel);
      ExistingMuseum museum = museumStruct.toExistingMuseum(newExhbtnModel.museum);

      return exhibitionStruct.toExistingExhibition(newExhbtnModel, museum);
    }
    return null;
  }

  @Override
  public List<ExistingExhibition> getExhibitionsByMuseumId(int id) {
    List<ExhibitionModel> actualList = exhibitionMapper.findExhibitionModelsByMuseumId(id);
    return toListExhibitions(actualList);
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
