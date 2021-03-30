package service.mapper;

import museum.domen.AuthorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.model.author.BaseAuthor;
import service.model.author.ExistingAuthor;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AuthorStruct {

  @Mapping(target = "fullName", source = "authorModel.fullName")
  @Mapping(target = "id", source = "authorModel.id")
  ExistingAuthor toExistingAuthor(AuthorModel authorModel);

  @Mapping(target = "fullName", source = "fullName")
  AuthorModel toAuthorModel(BaseAuthor author);

  @Mapping(target = "fullName", source = "fullName")
  @Mapping(target = "id", source = "id")
  AuthorModel toAuthorModel(ExistingAuthor author);

  default List<ExistingAuthor> toListExistingAuthor(List<AuthorModel> users) {
    return users
      .stream()
      .map(this::toExistingAuthor)
      .collect(Collectors.toList());
  }
}
