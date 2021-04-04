package service.internal.impl;

import museum.domen.ExhibitionModel;
import museum.domen.MuseumModel;
import museum.mapper.ExhibitionMapper;
import museum.mapper.MuseumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.internal.ExhibitionService;
import service.mapper.ExhibitionStruct;
import service.mapper.MuseumStruct;
import service.model.exhibition.BaseExhibition;
import service.model.exhibition.ExistingExhibition;
import service.model.museum.ExistingMuseum;
import src.model.MuseumStateEnum;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {


  private final ExhibitionMapper exhibitionMapper;
  private final ExhibitionStruct exhibitionStruct;
  private final MuseumStruct museumStruct;
  private final MuseumMapper museumMapper;

  @Autowired
  public ExhibitionServiceImpl(final ExhibitionStruct exhibitionStruct, final MuseumMapper museumMapper, final ExhibitionMapper exhibitionMapper, final MuseumStruct museumStruct) {
    this.exhibitionStruct = exhibitionStruct;
    this.exhibitionMapper = exhibitionMapper;
    this.museumStruct = museumStruct;
    this.museumMapper = museumMapper;
  }

  private List<ExistingExhibition> toListExhibitions(List<ExhibitionModel> actualList) {
    List<ExistingExhibition> exhibitionList = new ArrayList<>();
    for (ExhibitionModel exhibitionModel : actualList) {
      ExistingMuseum museum = museumStruct.toExistingMuseum(exhibitionModel.getMuseum());
      exhibitionList.add(exhibitionStruct.toExistingExhibition(exhibitionModel, museum.getId()));
    }
    return exhibitionList;
  }

  @Override
  public List<ExistingExhibition> getAllExhibitions() {
    Iterable<ExhibitionModel> exhibitionModels = exhibitionMapper.findExhibitionModelsByMuseumState(MuseumStateEnum.ACTIVE);
    List<ExhibitionModel> actualList = new ArrayList<>();
    while (exhibitionModels.iterator().hasNext()) {
      actualList.add(exhibitionModels.iterator().next());
    }
    return toListExhibitions(actualList);
  }

  @Override
  public ExistingExhibition createExhibition(BaseExhibition exhibition) {

    ExhibitionModel exhibitionModel = exhibitionStruct.toExhibitionModel(exhibition, museumMapper.findById((long) exhibition.getMuseumId()));

    ExhibitionModel newExhbtnModel = exhibitionMapper.save(exhibitionModel);
    return exhibitionStruct.toExistingExhibition(newExhbtnModel, newExhbtnModel.getMuseum().getId()  );

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
      ExistingMuseum museum = museumStruct.toExistingMuseum(newExhbtnModel.getMuseum());

      return exhibitionStruct.toExistingExhibition(newExhbtnModel, museum.getId());
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
